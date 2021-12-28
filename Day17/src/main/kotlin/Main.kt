import java.io.File

fun main(args: Array<String>) {
    val file = File("input.txt")
    val rawString = file.readText()

    val solver = Solver()

    val part1Result = solver.solvePart1(rawString)
    println("Part 1 result: $part1Result")
}