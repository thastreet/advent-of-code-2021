class Solver {
    fun solvePart1(values: List<Int>): Int =
        values.countGreaterThanPrevious()

    fun solvePart2(values: List<Int>): Int =
        values
            .windowed(3, 1, false)
            .map { window -> window.sum() }
            .countGreaterThanPrevious()

    private fun List<Int>.countGreaterThanPrevious(): Int =
        this
            .zipWithNext()
            .count { it.second > it.first }
}