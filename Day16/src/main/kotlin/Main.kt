import java.io.File
import java.math.BigInteger

fun main(args: Array<String>) {
    val file = File("input.txt")
    val string = file.readText()

    val valueWithoutLeadingZeros = BigInteger(string, 16).toString(2)
    val valueWithLeadingZeros = String.format("%" + string.length * 4 + "s", valueWithoutLeadingZeros).replace(" ", "0")

    parsePacket(valueWithLeadingZeros)
}

private fun parsePacket(binaryValue: String) {
    val versionBinary = binaryValue.take(3)
    val typeIdBinary = binaryValue.drop(versionBinary.length).take(3)

    val headerLength = versionBinary.length + typeIdBinary.length

    val version = versionBinary.toInt(2)
    val typeId = typeIdBinary.toInt(2)

    val type = if (typeId == 4) Type.LITERAL else Type.OPERATOR

    when (type) {
        Type.LITERAL -> {
            val groups = binaryValue.drop(headerLength).windowed(5, 5, partialWindows = false)

            val result = groups
                .fold("") { acc, s -> acc + s.drop(1) }
                .toInt(2)
        }
        Type.OPERATOR -> {
            val lengthTypeId = binaryValue.drop(headerLength).take(1)
            val lengthType = lengthTypeId.toInt(2)

            if (lengthType == 0) {
                val totalLengthOfSubPacketsBinary = binaryValue.drop(headerLength + lengthTypeId.length).take(15)
                val totalLengthOfSubPackets = totalLengthOfSubPacketsBinary.toInt(2)
            } else {
                val numberOfSubPacketsBinary = binaryValue.drop(headerLength + lengthTypeId.length).take(11)
                val numberOfSubPackets = numberOfSubPacketsBinary.toInt(2)
            }
        }
    }
}

private enum class Type {
    LITERAL,
    OPERATOR
}