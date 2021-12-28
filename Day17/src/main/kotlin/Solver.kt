import kotlin.math.abs

class Solver {
    fun solvePart1(input: String): Int =
        getAllInRange(input).maxOf { it.second.highestY }

    fun solvePart2(input: String): Int =
        getAllInRange(input).size

    private fun getAllInRange(input: String): List<Pair<Pair<Int, Int>, InRangeResult>> {
        val ranges = parseTargetRanges(input)
        val targetXRange = ranges.first
        val targetYRange = ranges.second

        val potentialXVels = findPotentialXVels(targetXRange)
        val potentialYVels = findPotentialYVels(targetYRange)
        val potentialVels: List<Pair<Int, Int>> =
            potentialXVels
                .map { xVel ->
                    potentialYVels.map { yVel -> Pair(xVel, yVel) }
                }
                .flatten()

        return potentialVels
            .map { isInRange(it, targetXRange, targetYRange) }
            .filter { it.second.inRange }
    }

    private fun parseTargetRanges(input: String): Pair<IntRange, IntRange> {
        val stripPrefix = input.substring("target area: ".length)
        val parts = stripPrefix.split(", ")

        val targetXRange: IntRange =
            parts[0]
                .substring("x=".length)
                .split("..")
                .let {
                    IntRange(it[0].toInt(), it[1].toInt())
                }

        val targetYRange: IntRange =
            parts[1]
                .substring("y=".length)
                .split("..")
                .let {
                    IntRange(it[0].toInt(), it[1].toInt())
                }

        return Pair(targetXRange, targetYRange)
    }

    private data class InRangeResult(
        val inRange: Boolean,
        val highestY: Int = 0
    )

    private fun isInRange(
        potential: Pair<Int, Int>,
        targetXRange: IntRange,
        targetYRange: IntRange
    ): Pair<Pair<Int, Int>, InRangeResult> {
        var x = 0
        var y = 0

        var highestY = y

        var xVel = potential.first
        var yVel = potential.second

        while (y >= targetYRange.first) {
            x += xVel
            y += yVel

            if (y > highestY)
                highestY = y

            if (x in targetXRange && y in targetYRange)
                return Pair(potential, InRangeResult(true, highestY))

            xVel += when {
                xVel > 0 -> -1
                xVel < 0 -> 1
                else -> 0
            }

            yVel -= 1
        }

        return Pair(potential, InRangeResult(false))
    }

    private fun findPotentialXVels(targetXRange: IntRange): List<Int> =
        (1..targetXRange.last)
            .filter { testX ->
                var x = 0
                var xVel = testX

                while (xVel != 0) {
                    x += xVel

                    if (x in targetXRange)
                        return@filter true

                    xVel -= 1
                }

                return@filter false
            }

    private fun findPotentialYVels(targetYRange: IntRange): List<Int> =
        (targetYRange.first..abs(targetYRange.first))
            .filter { testY ->
                var y = 0
                var yVel = testY

                while (y >= targetYRange.last) {
                    y += yVel

                    if (y in targetYRange)
                        return@filter true

                    yVel -= 1
                }

                return@filter false
            }
}