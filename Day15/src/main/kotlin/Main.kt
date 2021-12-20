import java.io.File

fun main(args: Array<String>) {
    val file = File("input.txt")
    val grid = file.readLines().map { line -> line.map { it.toString().toInt() } }

    val solver = Solver()

    val part1Result = solver.solvePart1(grid)

    println("Part 1 result: $part1Result")
}