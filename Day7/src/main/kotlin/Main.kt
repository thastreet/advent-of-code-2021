import java.io.File

fun main(args: Array<String>) {
    val file = File("input.txt")
    val startPositions = file.readText().split(",").map { it.toInt() }

    val solver = Solver()

    val part1Answer = solver.solvePart1(startPositions)
    println("Part 1 answer: $part1Answer")

    val part2Answer = solver.solvePart2(startPositions)
    println("Part 2 answer: $part2Answer")
}