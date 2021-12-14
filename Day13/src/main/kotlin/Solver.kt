typealias Grid = List<List<Boolean>>

class Solver {
    fun solvePart1(lines: List<String>): Int {
        val grid = getGrid(lines)
        val instructions = getInstructions(lines)
        val firstInstruction = instructions.first()

        val foldedGrid = grid.fold(firstInstruction)

        return countDots(foldedGrid)
    }

    fun solvePart2(lines: List<String>): String {
        val instructions = getInstructions(lines)

        val foldedGrid = instructions.fold(getGrid(lines)) { acc, instruction ->
            acc.fold(instruction)
        }

        return foldedGrid.print()
    }

    private fun Grid.print(): String {
        var result = ""
        forEachIndexed { index, row ->
            row.forEach {
                result += if (it) "#" else "."
            }

            if (index < size - 1) {
                result += "\n"
            }
        }
        return result
    }

    private fun Grid.fold(instruction: Pair<String, Int>): Grid =
        when (instruction.first) {
            "x" -> foldLeft(this, instruction.second)
            "y" -> foldUp(this, instruction.second)
            else -> throw IllegalArgumentException()
        }

    private fun getInstructions(lines: List<String>): List<Pair<String, Int>> =
        lines
            .filter { it.contains("=") }
            .map { line ->
                val lineParts = line.split(" ")
                val parts = lineParts.last().split("=")
                Pair(parts[0], parts[1].toInt())
            }

    private fun getGrid(lines: List<String>): Grid {
        val coordinates: List<Pair<Int, Int>> =
            lines
                .filter { it.contains(",") }
                .map { line ->
                    val parts = line.split(",")
                    Pair(parts[0].toInt(), parts[1].toInt())
                }

        val width = coordinates.maxOf { it.first } + 1
        val height = coordinates.maxOf { it.second } + 1

        return List(height) { y ->
            List(width) { x ->
                Pair(x, y) in coordinates
            }
        }
    }

    private fun countDots(grid: Grid): Int =
        grid.flatten().count { it }

    private fun foldUp(grid: Grid, index: Int): Grid =
        List(index) { y ->
            List(grid.first().size) { x ->
                grid[y][x] or grid[transpose(index, y)][x]
            }
        }

    private fun foldLeft(grid: Grid, index: Int): Grid =
        List(grid.size) { y ->
            List(index) { x ->
                grid[y][x] or grid[y][transpose(index, x)]
            }
        }

    private fun transpose(index: Int, coordinate: Int): Int =
        2 * index - coordinate
}