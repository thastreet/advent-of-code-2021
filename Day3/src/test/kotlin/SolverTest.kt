import org.junit.Assert
import org.junit.Test

class SolverTest {
    private val solver = Solver()

    private val example = listOf(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010"
    )

    @Test
    fun `given empty list expect solvePart1() returns 0`() {
        val result = solver.solvePart1(emptyList())
        Assert.assertEquals(0, result)
    }

    @Test
    fun `given example expect solvePart1() returns 198`() {
        val result = solver.solvePart1(example)
        Assert.assertEquals(198, result)
    }

    @Test
    fun `given empty list expect solvePart2() returns 0`() {
        val result = solver.solvePart2(emptyList())
        Assert.assertEquals(0, result)
    }

    @Test
    fun `given example expect solvePart2() returns 230`() {
        val result = solver.solvePart2(example)
        Assert.assertEquals(230, result)
    }
}