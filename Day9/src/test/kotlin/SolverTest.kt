import org.junit.Test
import kotlin.test.assertEquals

class SolverTest {
    private val example = """
        2199943210
        3987894921
        9856789892
        8767896789
        9899965678
    """.trimIndent()

    private val lines = example.split("\n").map { line ->
        line.map { it.toString().toInt() }
    }

    private val solver = Solver()

    @Test
    fun `given example then solvePart1() returns correct value`() {
        val answer = solver.solvePart1(lines)
        assertEquals(15, answer)
    }

    @Test
    fun `given example then solvePart2() returns correct value`() {
        val answer = solver.solvePart2(lines)
        assertEquals(1134, answer)
    }
}