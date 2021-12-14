import java.io.File

fun main(args: Array<String>) {
    val file = File("input.txt")
    val lines = file.readLines()

    val solver = Solver()

    val part1Response = solver.solvePart1(lines)
    println("Part 1 answer: $part1Response")

    val part2Response = solver.solvePart2(lines)
    println("Part 2 answer: ")
    println(part2Response)
}