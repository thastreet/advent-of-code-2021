import java.io.File

fun main(args: Array<String>) {
    val file = File("input.txt")

    val state = file.readText().split(",").map { it.toInt() }.toMutableList()

    val solver = Solver()

    val part1Result = solver.solvePart1(state)
    println("Part 1 result: $part1Result")

    val part2Result = solver.solvePart2(state)
    println("Part 2 result: $part2Result")
}