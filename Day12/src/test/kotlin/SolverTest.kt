import org.junit.Test
import kotlin.test.assertEquals

class SolverTest {

    private val example1 = """
        start-A
        start-b
        A-c
        A-b
        b-d
        A-end
        b-end
    """.trimIndent()

    private val example1Lines =
        example1.split("\n")

    private val example2 = """
        dc-end
        HN-start
        start-kj
        dc-start
        dc-HN
        LN-dc
        HN-end
        kj-sa
        kj-HN
        kj-dc
    """.trimIndent()

    private val example2Lines =
        example2.split("\n")

    private val example3 = """
        fs-end
        he-DX
        fs-he
        start-DX
        pj-DX
        end-zg
        zg-sl
        zg-pj
        pj-he
        RW-he
        fs-DX
        pj-RW
        zg-RW
        start-pj
        he-WI
        zg-he
        pj-fs
        start-RW
    """.trimIndent()

    private val example3Lines =
        example3.split("\n")

    private val solver = Solver()

    @Test
    fun `given example1 then solvePart1() returns correct value`() {
        val result = solver.solvePart1(example1Lines)
        assertEquals(10, result)
    }

    @Test
    fun `given example2 then solvePart1() returns correct value`() {
        val result = solver.solvePart1(example2Lines)
        assertEquals(19, result)
    }

    @Test
    fun `given example3 then solvePart1() returns correct value`() {
        val result = solver.solvePart1(example3Lines)
        assertEquals(226, result)
    }
}