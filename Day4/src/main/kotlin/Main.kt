import java.io.File

typealias Board = List<List<Case>>

fun main(args: Array<String>) {
    val file = File("input.txt")
    val lines = file.readLines().takeIf { it.isNotEmpty() } ?: throw IllegalArgumentException("Lines are empty!")

    val boardSize = Helper.getBoardSize(lines)
    val drawnNumbers = Helper.getDrawnNumbers(lines)
    val boards = Helper.buildBoards(lines, boardSize)

    val solver = Solver()

    val part1Answer = solver.solvePart1(drawnNumbers, boards, boardSize)
    println("Part 1 answer: $part1Answer")

    val part2Answer = solver.solvePart2(drawnNumbers, boards, boardSize)
    println("Part 2 answer: $part2Answer")
}