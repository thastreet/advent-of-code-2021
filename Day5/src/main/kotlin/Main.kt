import java.awt.Point
import java.io.File

fun main(args: Array<String>) {
    val file = File("input.txt")

    val segments = file
        .readLines()
        .map { line ->
            line.split(" -> ").let { segmentParts ->
                if (segmentParts.size != 2) throw IllegalArgumentException("Expected segment parts size to be 2!")

                val originParts = segmentParts[0].split(",")
                val destinationParts = segmentParts[1].split(",")

                if (originParts.size != 2 || destinationParts.size != 2) throw IllegalArgumentException("Expected origin and destination parts size to be 2!")

                Segment(
                    Point(originParts[0].toInt(), originParts[1].toInt()),
                    Point(destinationParts[0].toInt(), destinationParts[1].toInt())
                )
            }
        }

    val solver = Solver()

    val part1Answer = solver.solvePart1(segments)
    println("Part 1 answer: $part1Answer")

    val part2Answer = solver.solvePart2(segments)
    println("Part 2 answer: $part2Answer")
}