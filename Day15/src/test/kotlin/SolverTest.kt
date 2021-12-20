import org.junit.Test
import kotlin.test.assertEquals

class SolverTest {
    private val example = """
        1163751742
        1381373672
        2136511328
        3694931569
        7463417111
        1319128137
        1359912421
        3125421639
        1293138521
        2311944581
    """.trimIndent()

    private val grid = example.split("\n").map { line -> line.map { it.toString().toInt() } }

    private val solver = Solver()

    @Test
    fun `given example then solvePart1() returns correct value`() {
        val result = solver.solvePart1(grid)
        assertEquals(40, result)
    }
}