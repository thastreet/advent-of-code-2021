import java.io.File

fun main(args: Array<String>) {
    val file = File("input.txt")
    val values = file.readIntLines()

    val solver = Solver()

    val part1Result = solver.solvePart1(values)
    println("Part 1: $part1Result")

    val part2Result = solver.solvePart2(values)
    println("Part 2: $part2Result")
}

private fun File.readIntLines(): List<Int> =
    this
        .readLines()
        .mapNotNull { it.toIntOrNull() }