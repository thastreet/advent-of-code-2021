class Solver {
    companion object {
        private const val FLASHED = -1
    }

    fun solvePart1(lines: List<List<Int>>): Int {
        val width = lines.first().size
        val height = lines.size
        val state = createState(lines)

        var totalFlashes = 0

        repeat(100) {
            totalFlashes += incrementAll(state, width, height)
            resetFlashed(state, width, height)
        }

        return totalFlashes
    }

    fun solvePart2(lines: List<List<Int>>): Int {
        val width = lines.first().size
        val height = lines.size
        val state = createState(lines)

        var step = 1

        while (true) {
            incrementAll(state, width, height)
            resetFlashed(state, width, height)

            val allFlash = state.flatten().all { it == 0 }
            if (allFlash) {
                return step
            } else {
                ++step
            }
        }
    }

    private fun incrementAll(state: MutableList<MutableList<Int>>, width: Int, height: Int): Int {
        var flashes = 0

        for (y in 0 until height) {
            for (x in 0 until width) {
                flashes += increment(state, x, y, width, height)
            }
        }

        return flashes
    }

    private fun resetFlashed(state: MutableList<MutableList<Int>>, width: Int, height: Int) {
        for (y in 0 until height) {
            for (x in 0 until width) {
                if (state[y][x] == FLASHED) {
                    state[y][x] = 0
                }
            }
        }
    }

    private fun createState(lines: List<List<Int>>): MutableList<MutableList<Int>> =
        lines
            .map {
                it.toMutableList()
            }
            .toMutableList()

    private fun increment(state: MutableList<MutableList<Int>>, x: Int, y: Int, width: Int, height: Int): Int {
        if (state[y][x] > FLASHED) {
            state[y][x] = state[y][x] + 1

            if (state[y][x] > 9) {
                state[y][x] = FLASHED
                return flash(state, x, y, width, height)
            }
        }

        return 0
    }

    private fun flash(state: MutableList<MutableList<Int>>, x: Int, y: Int, width: Int, height: Int): Int {
        var flashes = 1

        flashes += propagateFlash(state, x - 1, y - 1, width, height)
        flashes += propagateFlash(state, x, y - 1, width, height)
        flashes += propagateFlash(state, x + 1, y - 1, width, height)
        flashes += propagateFlash(state, x + 1, y, width, height)
        flashes += propagateFlash(state, x + 1, y + 1, width, height)
        flashes += propagateFlash(state, x, y + 1, width, height)
        flashes += propagateFlash(state, x - 1, y + 1, width, height)
        flashes += propagateFlash(state, x - 1, y, width, height)

        return flashes
    }

    private fun propagateFlash(
        state: MutableList<MutableList<Int>>,
        newX: Int,
        newY: Int,
        width: Int,
        height: Int
    ): Int {
        if (newX >= 0 && newX <= width - 1 && newY >= 0 && newY <= height - 1) {
            return increment(state, newX, newY, width, height)
        }

        return 0
    }
}