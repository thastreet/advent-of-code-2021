import kotlin.math.abs

class Solver {
    fun solvePart1(startPositions: List<Int>): Int {
        val min = startPositions.minOrNull() ?: throw IllegalArgumentException("No min!")
        val max = startPositions.maxOrNull() ?: throw IllegalArgumentException("No max!")

        return (min..max).minOf { base ->
            startPositions
                .sumOf { current ->
                    abs(base - current)
                }
        }
    }

    fun solvePart2(startPositions: List<Int>): Int {
        val min = startPositions.minOrNull() ?: throw IllegalArgumentException("No min!")
        val max = startPositions.maxOrNull() ?: throw IllegalArgumentException("No max!")

        return (min..max).minOf { base ->
            startPositions
                .sumOf { current ->
                    val diff = abs(base - current)
                    (1..diff)
                        .fold(0) { acc, i ->
                            acc + i
                        }
                        .toInt()
                }
        }
    }
}