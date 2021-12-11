import java.awt.Point
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

data class Segment(val origin: Point, val destination: Point) {
    fun getLines(includeDiagonals: Boolean): List<Point> {
        val straightLines = getStraightLines()
        val diagonalLines = if (includeDiagonals) getDiagonalLines() else emptyList()
        return straightLines + diagonalLines
    }

    private fun getStraightLines(): List<Point> =
        if (origin.x == destination.x || origin.y == destination.y)
            if (origin.x == destination.x)
                (min(origin.y, destination.y)..max(origin.y, destination.y)).map {
                    Point(origin.x, it)
                }
            else
                (min(origin.x, destination.x)..max(origin.x, destination.x)).map {
                    Point(it, origin.y)
                }
        else
            emptyList()

    private fun getDiagonalLines(): List<Point> {
        val diffX = abs(origin.x - destination.x)
        val diffY = abs(origin.y - destination.y)

        return if (diffX == diffY) {
            val midPoints =
                (1 until diffX).map {
                    Point(
                        if (origin.x < destination.x) origin.x + it else origin.x - it,
                        if (origin.y < destination.y) origin.y + it else origin.y - it
                    )
                }

            listOf(origin) + midPoints + listOf(destination)
        } else {
            emptyList()
        }
    }
}