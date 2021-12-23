import java.math.BigInteger

class Solver {
    private var i = 0

    fun solvePart1(hexString: String): Int {
        val binaryString = getBinaryString(hexString)
        val packets = advance(binaryString, Context.None)
        val flatten = getSubPackets(packets)
        return flatten.sumOf { it.version }
    }

    private fun getBinaryString(hexString: String): String {
        val valueWithoutLeadingZeros = BigInteger(hexString, 16).toString(2)
        return String.format("%" + hexString.length * 4 + "s", valueWithoutLeadingZeros).replace(" ", "0")
    }

    private fun getSubPackets(current: List<Packet>): List<Packet> =
        if (current.isEmpty()) {
            current
        } else {
            current + getSubPackets(current.map { it.subPackets }.flatten())
        }

    private fun advance(input: String, context: Context): List<Packet> {
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

                packets.add(Packet(version, type, sum.toBigInteger(2), emptyList()))
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

    private sealed interface Context {
        object None : Context

        data class TotalLength(
            val value: Int
        ): Context

        data class Count(
            val value: Int
        ): Context
    }

    private data class Packet(
        val version: Int,
        val typeId: Int,
        val value: BigInteger?,
        val subPackets: List<Packet>
    )
}