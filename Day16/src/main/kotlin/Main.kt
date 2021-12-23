import java.io.File
import java.math.BigInteger

var i = 0

fun main(args: Array<String>) {
    val file = File("input.txt")
    val string = file.readText()

    val valueWithoutLeadingZeros = BigInteger(string, 16).toString(2)
    val valueWithLeadingZeros = String.format("%" + string.length * 4 + "s", valueWithoutLeadingZeros).replace(" ", "0")
    val list = advance(valueWithLeadingZeros, Context.None)
}

fun advance(input: String, context: Context): List<Packet> {
    val packets = mutableListOf<Packet>()
    val start = i

    while (i < input.length) {
        val version = input.substring(i, i + 3).toInt(2)
        i += 3

        val type = input.substring(i, i + 3).toInt(2)
        i += 3

        if (type == 4) {
            var sum = ""
            var firstOfGroupBin: Char
            do {
                firstOfGroupBin = input[i]
                i++

                val restBin = input.substring(i, i + 4)
                i += 4

                sum += restBin
            } while (firstOfGroupBin != '0')

            packets.add(Packet(version, type, sum.toInt(2), emptyList()))
        } else {
            val lengthType = input[i].toString().toInt(2)
            i++

            if (lengthType == 0) {
                val totalLength = input.substring(i, i + 15).toInt(2)
                i += 15
                packets.add(Packet(version, type, null, advance(input, Context.TotalLength(totalLength))))
            } else {
                val count = input.substring(i, i + 11).toInt(2)
                i += 11
                packets.add(Packet(version, type, null, advance(input, Context.Count(count))))
            }
        }

        if (context == Context.None || context is Context.Count && packets.size == context.value || context is Context.TotalLength && i - start >= context.value) {
            break
        }
    }

    return packets
}

sealed interface Context {
    object None : Context

    data class TotalLength(
        val value: Int
    ): Context

    data class Count(
        val value: Int
    ): Context
}

data class Packet(
    val version: Int,
    val typeId: Int,
    val value: Int?,
    val subPackets: List<Packet>
)

private enum class Type {
    LITERAL,
    OPERATOR
}