import java.io.File
import java.lang.Math.ceil
import java.lang.Math.floor
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sign

fun main(args: Array<String>) {
    val file = File("input.txt")
    val lines = file.readLines()

    val solver = Solver()

    val answerPart1 = solver.solvePart1(lines)
    println("Answer part 1: $answerPart1")

    val answerPart2 = solver.solvePart2(lines)
    println("Answer part 2: $answerPart2")
}