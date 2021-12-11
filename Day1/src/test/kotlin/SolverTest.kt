import org.junit.Assert.*
import org.junit.Test

class SolverTest {
    private val solver = Solver()

    private val example = listOf(
        199,
        200,
        208,
        210,
        200,
        207,
        240,
        269,
        260,
        263
    )

    @Test
    fun `given empty list expect solvePart1() returns 0`() {
        val count = solver.solvePart1(emptyList())
        assertEquals(0, count)
    }

    @Test
    fun `given example expect solvePart1() returns 7`() {
        val count = solver.solvePart1(example)
        assertEquals(7, count)
    }

    @Test
    fun `given empty list expect solvePart2() returns 0`() {
        val count = solver.solvePart2(emptyList())
        assertEquals(0, count)
    }

    @Test
    fun `given example expect solvePart2() returns 5`() {
        val count = solver.solvePart2(example)
        assertEquals(5, count)
    }
}