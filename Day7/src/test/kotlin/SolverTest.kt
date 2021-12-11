import org.junit.Test
import kotlin.test.assertEquals

class SolverTest {

    private val example = listOf(16, 1, 2, 0, 4, 2, 7, 1, 2, 14)
    private val solver = Solver()

    @Test
    fun `given example then solvePart1() returns correct value`() {
        val answer = solver.solvePart1(example)
        assertEquals(37, answer)
    }

    @Test
    fun `given example then solvePart2() returns correct value`() {
        val answer = solver.solvePart2(example)
        assertEquals(168, answer)
    }
}