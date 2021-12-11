import org.junit.Test
import kotlin.test.assertEquals

class SolverTest {

    private val example = listOf(3, 4, 3, 1, 2)
    private val solver = Solver()

    @Test
    fun `given example then solvePart1() returns correct value`() {
        val answer = solver.solvePart1(example)
        assertEquals(5934, answer)
    }

    @Test
    fun `given example then solvePart2() returns correct value`() {
        val answer = solver.solvePart2(example)
        assertEquals(26984457539, answer)
    }
}