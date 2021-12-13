import java.io.File

fun main(args: Array<String>) {
    val file = File("input.txt")
    val lines = file.readLines()

    val solver = Solver()

    val part1Answer = solver.solvePart1(lines)
    println("Part 1 answer: $part1Answer")

    val part2Answer = solver.solvePart2(lines)
    println("Part 2 answer: $part2Answer")
}