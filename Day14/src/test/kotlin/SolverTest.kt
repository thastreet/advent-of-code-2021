import org.junit.Test
import kotlin.test.assertEquals

class SolverTest {

    private val example = """
        NNCB

        CH -> B
        HH -> N
        CB -> H
        NH -> C
        HB -> C
        HC -> B
        HN -> C
        NN -> C
        BH -> H
        NC -> B
        NB -> B
        BN -> B
        BB -> N
        BC -> B
        CC -> N
        CN -> C
    """.trimIndent()

    private val lines = example.split("\n")

    private val solver = Solver()

    @Test
    fun `given example then solvePart1() returns correct value`() {
        val result = solver.solvePart1(lines)
        assertEquals(1588, result)
    }

    @Test
    fun `given example then solvePart2() returns correct value`() {
        val result = solver.solvePart2(lines)
        assertEquals(2188189693529, result)
    }
}