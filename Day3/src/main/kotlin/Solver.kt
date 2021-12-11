class Solver {
    fun solvePart1(lines: List<String>): Int {
        if (lines.isEmpty()) return 0

        val length = lines.first().length
        if (lines.any { it.length != length }) {
            throw IllegalArgumentException("Each lines must be of equals size!")
        }

        val gammaRateString = getMostCommonBitString(lines, length)

        if (gammaRateString.length != length) {
            throw IllegalArgumentException("gammaRateString length must be of equal to lines length")
        }

        val epsilonRateString = gammaRateString.invBitString()

        val gammaRate = gammaRateString.bitStringToDecimalInt()
        val epsilonRate = epsilonRateString.bitStringToDecimalInt()

        return gammaRate * epsilonRate
    }

    private fun getMostCommonBitString(lines: List<String>, length: Int): String =
        (0 until length)
            .map { index -> resolveBitAtIndex(lines, index, ResolutionMode.MOST_COMMON) }
            .joinToString("")

    fun solvePart2(lines: List<String>): Int {
        if (lines.isEmpty()) return 0

        val length = lines.first().length
        if (lines.any { it.length != length }) {
            throw IllegalArgumentException("Each lines must be of equals size!")
        }

        val oxygenGeneratorRating = findRating(lines, length, ResolutionMode.MOST_COMMON, 1) ?: 0
        val co2ScrubberRating = findRating(lines, length, ResolutionMode.LEAST_COMMON, 0) ?: 0

        return oxygenGeneratorRating * co2ScrubberRating
    }

    private fun findRating(lines: List<String>, length: Int, resolutionMode: ResolutionMode, fallback: Int): Int? =
        (0 until length)
            .fold(lines) { acc, index ->
                if (acc.size == 1) return@fold acc

                val resolvedBit = resolveBitAtIndex(acc, index, resolutionMode, fallback)

                acc.filter { line ->
                    line[index].toBit() == resolvedBit
                }
            }.firstOrNull()?.bitStringToDecimalInt()

    private fun resolveBitAtIndex(
        lines: List<String>,
        index: Int,
        resolutionMode: ResolutionMode,
        fallback: Int? = null
    ): Int? {
        val column = lines.map { line ->
            line[index].toBit()
        }

        return when (resolutionMode) {
            ResolutionMode.MOST_COMMON -> getMostCommonBitInColumn(column)
            ResolutionMode.LEAST_COMMON -> getLeastCommonBitInColumn(column)
        } ?: fallback
    }

    private enum class ResolutionMode {
        MOST_COMMON,
        LEAST_COMMON
    }

    private fun Char.toBit(): Int =
        toString().toInt()

    private fun getMostCommonBitInColumn(column: List<Int>): Int? {
        val zeros = column.count { it == 0 }
        val ones = column.count { it == 1 }

        return when {
            zeros > ones -> 0
            ones > zeros -> 1
            else -> null
        }
    }

    private fun getLeastCommonBitInColumn(column: List<Int>): Int? =
        when (getMostCommonBitInColumn(column)) {
            0 -> 1
            1 -> 0
            else -> null
        }

    private fun String.invBitString(): String =
        map { if (it == '0') '1' else '0' }.joinToString("")

    private fun String.bitStringToDecimalInt(): Int =
        Integer.parseInt(this, 2)
}