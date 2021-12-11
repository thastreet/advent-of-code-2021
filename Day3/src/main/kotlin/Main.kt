import java.io.File

fun main(args: Array<String>) {
    val file = File("input.txt")
    val lines = file.readLines().takeIf { it.isNotEmpty() } ?: return

    val solver = Solver()

    val part1Result = solver.solvePart1(lines)
    println("Part 1 result: $part1Result")

    val part2Result = solver.solvePart2(lines)
    println("Part 2 result: $part2Result")
}