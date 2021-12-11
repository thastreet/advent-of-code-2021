import org.junit.Assert
import org.junit.Test

class SolverTest {
    private val solver = Solver()

    private val example = listOf(
        Command(Direction.FORWARD, 5),
        Command(Direction.DOWN, 5),
        Command(Direction.FORWARD, 8),
        Command(Direction.UP, 3),
        Command(Direction.DOWN, 8),
        Command(Direction.FORWARD, 2)
    )

    @Test
    fun `given empty list expect solvePart1() returns 0`() {
        val result = solver.solvePart1(emptyList())
        Assert.assertEquals(0, result)
    }

    @Test
    fun `given example expect solvePart1() returns 150`() {
        val result = solver.solvePart1(example)
        Assert.assertEquals(150, result)
    }

    @Test
    fun `given empty list expect solvePart2() returns 0`() {
        val result = solver.solvePart2(emptyList())
        Assert.assertEquals(0, result)
    }

    @Test
    fun `given example expect solvePart2() returns 900`() {
        val result = solver.solvePart2(example)
        Assert.assertEquals(900, result)
    }
}