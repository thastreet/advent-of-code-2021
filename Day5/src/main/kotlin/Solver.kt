import java.awt.Point

class Solver {
    fun solvePart1(segments: List<Segment>): Int =
        solve(segments, false)

    fun solvePart2(segments: List<Segment>): Int =
        solve(segments, true)

    private fun solve(segments: List<Segment>, includeDiagonalLines: Boolean): Int {
        val lines: List<List<Point>> = segments.map { it.getLines(includeDiagonalLines) }
        val points = lines.flatten().distinct()

        var numberOfDesiredIntersects = 0

        for (point in points) {
            var pointInLineCount = 0
            for (line in lines) {
                if (line.contains(point)) {
                    ++pointInLineCount

                    if (pointInLineCount == 2) {
                        ++numberOfDesiredIntersects
                        break
                    }
                }
            }
        }

        return numberOfDesiredIntersects
    }
}