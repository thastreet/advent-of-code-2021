class Solver {
    fun solvePart1(initialState: List<Int>): Long =
        solve(initialState, 80)

    fun solvePart2(initialState: List<Int>): Long =
        solve(initialState, 256)

    private fun solve(initialState: List<Int>, days: Int): Long {
        val state = (0..9)
            .associateWith { key ->
                initialState.count { it == key }.toLong()
            }
            .toMutableMap()

        repeat(days) {
            state[7] = state.getValue(7) + state.getValue(0)
            state[9] = state.getValue(0)

            (1..9)
                .forEach {
                    state[it - 1] = state.getValue(it)
                }
        }

        return (0..8)
            .sumOf {
                state.getValue(it)
            }
    }
}