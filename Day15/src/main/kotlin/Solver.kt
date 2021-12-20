class Solver {
    fun solvePart1(grid: List<List<Int>>): Int {
        val width = grid.first().size
        val height = grid.size

        val dist = mutableMapOf<Point, Int>()
        val prev = mutableMapOf<Point, Point>()
        val nodes = mutableSetOf<Point>()

        for (y in 0 until height) {
            for (x in 0 until width) {
                val point = Point(x, y)
                dist[point] = if (x == 0 && y == 0) 0 else Int.MAX_VALUE
                nodes.add(point)
            }
        }

        while (nodes.isNotEmpty()) {
            val current = nodes.minByOrNull { dist.getValue(it) } ?: break
            nodes.remove(current)

            val top: Point? = Point(current.x, current.y - 1).takeIf { it.y >= 0 && nodes.contains(it) }
            val left: Point? = Point(current.x - 1, current.y).takeIf { it.x >= 0 && nodes.contains(it) }
            val right: Point? = Point(current.x + 1, current.y).takeIf { it.x <= width - 1 && nodes.contains(it) }
            val bottom: Point? = Point(current.x, current.y + 1).takeIf { it.y <= height - 1 && nodes.contains(it) }

            listOfNotNull(top, left, right, bottom).forEach {
                val newDist = dist.getValue(current) + grid[it.y][it.x]

                if (newDist < dist.getValue(it)) {
                    dist[it] = newDist
                    prev[it] = current
                }
            }
        }

        val path = backtrack(prev, width, height)

        return path
            .drop(1)
            .sumOf {
                grid[it.y][it.x]
            }
    }

    private fun backtrack(prev: Map<Point, Point>, width: Int, height: Int): List<Point> {
        val origin = Point(0, 0)

        val path = mutableListOf<Point>()
        var current: Point? = Point(width - 1, height - 1)

        do {
            current?.let {
                path.add(it)
            }

            val previous = prev[current]?.also {
                path.add(it)
            }

            current = prev[previous]
        } while (current != origin)

        path.add(current)

        return path.reversed()
    }

    data class Point(
        val x: Int,
        val y: Int
    )
}