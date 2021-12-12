class Solver {
    fun solvePart1(lines: List<List<Int>>): Int {
        val width = lines.first().size
        val height = lines.size

        val state = lines
            .map {
                it.toMutableList()
            }
            .toMutableList()

        var totalFlashes = 0

        repeat(100) {
            for (y in 0 until height) {
                for (x in 0 until width) {
                    totalFlashes += increment(state, x, y, width, height)
                }
            }

            for (y in 0 until height) {
                for (x in 0 until width) {
                    if (state[y][x] == -1) {
                        state[y][x] = 0
                    }
                }
            }
        }

        return totalFlashes
    }

    private fun increment(state: MutableList<MutableList<Int>>, x: Int, y: Int, width: Int, height: Int): Int {
        if (state[y][x] > -1) {
            state[y][x] = state[y][x] + 1

            if (state[y][x] > 9) {
                state[y][x] = -1
                return flash(state, x, y, width, height)
            }
        }

        return 0
    }

    private fun flash(state: MutableList<MutableList<Int>>, x: Int, y: Int, width: Int, height: Int): Int {
        var flashes = 1

        if (x - 1 >= 0 && y - 1 >= 0) {
            flashes += increment(state, x - 1, y - 1, width, height)
        }

        if (y - 1 >= 0) {
            flashes += increment(state, x, y - 1, width, height)
        }

        if (x + 1 <= width - 1 && y - 1 >= 0) {
            flashes += increment(state, x + 1, y - 1, width, height)
        }

        if (x + 1 <= width - 1) {
            flashes += increment(state, x + 1, y, width, height)
        }

        if (x + 1 <= width - 1 && y + 1 <= height - 1) {
            flashes += increment(state, x + 1, y + 1, width, height)
        }

        if (y + 1 <= height - 1) {
            flashes += increment(state, x, y + 1, width, height)
        }

        if (x - 1 >= 0 && y + 1 <= height - 1) {
            flashes += increment(state, x - 1, y + 1, width, height)
        }

        if (x - 1 >= 0) {
            flashes += increment(state, x - 1, y, width, height)
        }

        return flashes
    }
}