import java.io.File

fun main(args: Array<String>) {
    val file = File("input.txt")

    val lines = file.readLines()
        .map { line ->
            line.map {
                it.toString().toInt()
            }
        }

    val solver = Solver()

    val part1Result = solver.solvePart1(lines)
    println("Print 1 result: $part1Result")

    val part2Result = solver.solvePart2(lines)
    println("Print 2 result: $part2Result")
}