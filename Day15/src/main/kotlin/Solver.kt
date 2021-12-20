import java.util.*

class Solver {
    fun solvePart1(grid: List<List<Int>>): Int =
        solve(grid)

    fun solvePart2(grid: List<List<Int>>): Int {
        val width = grid.first().size
        val height = grid.size

        val growth = 5
        val maxNumber = 9
        val newWidth = width * growth
        val newHeight = height * growth

        val newGrid = MutableList(newHeight) {
            MutableList(newWidth) {
                0
            }
        }

        for (y in 0 until newHeight) {
            val heightIncrement = y / height

            for (x in 0 until newWidth) {
                val widthIncrement = x / width
                val totalIncrement = widthIncrement + heightIncrement

                val currentNumber = grid[y % height][x % width]
                val rawNewNumber = currentNumber + totalIncrement
                val newNumber = if (rawNewNumber > maxNumber) rawNewNumber - maxNumber * (rawNewNumber / maxNumber) else rawNewNumber
                newGrid[y][x] = newNumber
            }
        }

        return solve(newGrid)
    }

    private fun solve(grid: List<List<Int>>): Int {
        val width = grid.first().size
        val height = grid.size

        val dist = mutableMapOf<Point, Int>()
        val prev = mutableMapOf<Point, Point>()
        val nodes = PriorityQueue<Point> { o1, o2 ->
            dist.getValue(o1).compareTo(dist.getValue(o2))
        }

        for (y in 0 until height) {
            for (x in 0 until width) {
                val point = Point(x, y)
                dist[point] = if (x == 0 && y == 0) 0 else Int.MAX_VALUE
            }
        }

        nodes.add(Point(0, 0))

        val destination = Point(width - 1, height - 1)

        while (nodes.isNotEmpty()) {
            val current = nodes.remove()

            if (current == destination) break

            val top: Point? = Point(current.x, current.y - 1).takeIf { it.y >= 0 && dist[it] == Int.MAX_VALUE }
            val left: Point? = Point(current.x - 1, current.y).takeIf { it.x >= 0 && dist[it] == Int.MAX_VALUE }
            val right: Point? = Point(current.x + 1, current.y).takeIf { it.x <= width - 1 && dist[it] == Int.MAX_VALUE }
            val bottom: Point? = Point(current.x, current.y + 1).takeIf { it.y <= height - 1 && dist[it] == Int.MAX_VALUE }

            listOfNotNull(top, left, right, bottom).forEach {
                val newDist = dist.getValue(current) + grid[it.y][it.x]

                if (newDist < dist.getValue(it)) {
                    dist[it] = newDist
                    prev[it] = current
                }

                nodes.add(it)
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