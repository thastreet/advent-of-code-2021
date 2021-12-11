import java.io.File

fun main(args: Array<String>) {
    val file = File("input.txt")
    val lines = file.readLines().map { line ->
        line.map { it.toString().toInt() }
    }

    val solver = Solver()

    val part1Response = solver.solvePart1(lines)
    println("Part 1 response: $part1Response")

    val part2Response = solver.solvePart2(lines)
    println("Part 2 response: $part2Response")
}