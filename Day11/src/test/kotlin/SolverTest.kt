import org.junit.Test
import kotlin.test.assertEquals

class SolverTest {
    private val example = """
        5483143223
        2745854711
        5264556173
        6141336146
        6357385478
        4167524645
        2176841721
        6882881134
        4846848554
        5283751526
    """.trimIndent()

    private val lines = example.split("\n")
        .map { line ->
            line.map { it.toString().toInt() }
        }

    private val solver = Solver()

    @Test
    fun `given example then solvePart1() returns correct value`() {
        val result = solver.solvePart1(lines)
        assertEquals(1656, result)
    }
}