import org.junit.Test
import kotlin.test.assertEquals

class SolverTest {
    private val example = "target area: x=20..30, y=-10..-5"
    private val solver = Solver()

    @Test
    fun `given example then solvePart1() returns correct value`() {
        val result = solver.solvePart1(example)
        assertEquals(45, result)
    }

    @Test
    fun `given example then solvePart2() returns correct value`() {
        val result = solver.solvePart2(example)
        assertEquals(112, result)
    }
}