class Solver {
    companion object {
        private const val HIGHEST_POINT = 9
    }

    fun solvePart1(lines: List<List<Int>>): Int {
        val width: Int = lines.first().size
        val height: Int = lines.size

        return (0 until width * height)
            .filter { flatIndex ->
                val point = flatIndex.toPoint(width)
                val value = lines[point]
                value.isLowPoint(point, lines, width, height)
            }.sumOf { flatIndex ->
                val point = flatIndex.toPoint(width)
                lines[point] + 1
            }
    }

    fun solvePart2(lines: List<List<Int>>): Int {
        val width: Int = lines.first().size
        val height: Int = lines.size

        return (0 until width * height)
            .asSequence()
            .filter { flatIndex ->
                val point = flatIndex.toPoint(width)
                val value = lines[point]
                value.isLowPoint(point, lines, width, height)
            }
            .map { flatIndex ->
                val point = flatIndex.toPoint(width)
                search(point, lines, width, height)
            }
            .sortedByDescending { it.size }
            .take(3)
            .fold(1) { acc, points -> acc * points.size }
    }

    private fun search(
        currentPoint: Point,
        lines: List<List<Int>>,
        width: Int,
        height: Int,
    ): List<Point> =
        search(listOf(currentPoint), lines, width, height, emptyList())

    private fun search(
        currentPoints: List<Point>,
        lines: List<List<Int>>,
        width: Int,
        height: Int,
        basin: List<Point>
    ): List<Point> {
        val pointsToSearch = currentPoints
            .map { currentPoint ->
                val (x, y) = currentPoint

                val validSearchPointPredicate: (Point) -> Boolean = {
                    it != currentPoint && it !in basin && lines[it] != HIGHEST_POINT
                }

                val top: Point? =
                    (y - 1)
                        .takeIf { it >= 0 }
                        ?.let { Point(x, it) }
                        ?.takeIf(validSearchPointPredicate)

                val left: Point? =
                    (x - 1)
                        .takeIf { it >= 0 }
                        ?.let { Point(it, y) }
                        ?.takeIf(validSearchPointPredicate)

                val bottom: Point? =
                    (y + 1)
                        .takeIf { it <= height - 1 }
                        ?.let { Point(x, it) }
                        ?.takeIf(validSearchPointPredicate)

                val right: Point? =
                    (x + 1)
                        .takeIf { it <= width - 1 }
                        ?.let { Point(it, y) }
                        ?.takeIf(validSearchPointPredicate)

                listOfNotNull(top, left, bottom, right)
            }
            .flatten()
            .distinct()

        val newBasin = basin + pointsToSearch

        return if (pointsToSearch.isNotEmpty())
            search(
                pointsToSearch,
                lines,
                width,
                height,
                newBasin
            )
        else
            newBasin
    }

    private fun Int.isLowPoint(point: Point, lines: List<List<Int>>, width: Int, height: Int): Boolean {
        val (x, y) = point

        val top: Int? = (y - 1).takeIf { it >= 0 }?.let { lines[it][x] }
        val left: Int? = (x - 1).takeIf { it >= 0 }?.let { lines[y][it] }
        val bottom: Int? = (y + 1).takeIf { it <= height - 1 }?.let { lines[it][x] }
        val right: Int? = (x + 1).takeIf { it <= width - 1 }?.let { lines[y][it] }

        return top?.let { it > this } ?: true &&
                left?.let { it > this } ?: true &&
                bottom?.let { it > this } ?: true &&
                right?.let { it > this } ?: true
    }

    private data class Point(
        val x: Int,
        val y: Int
    )

    private fun Int.toPoint(width: Int): Point =
        Point(this % width, this / width)

    private operator fun List<List<Int>>.get(point: Point) = this[point.y][point.x]
}