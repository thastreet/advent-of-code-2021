import org.junit.Test
import kotlin.test.assertEquals

class SolverTest {

    private val example1 = """
        6,10
        0,14
        9,10
        0,3
        10,4
        4,11
        6,0
        6,12
        4,1
        0,13
        10,12
        3,4
        3,0
        8,4
        1,10
        2,14
        8,10
        9,0
        
        fold along y=7
        fold along x=5
    """.trimIndent()

    private val example1Lines =
        example1.split("\n")

    private val solver = Solver()

    @Test
    fun `given example1 then solvePart1() returns correct value`() {
        val result = solver.solvePart1(example1Lines)
        assertEquals(17, result)
    }

    @Test
    fun `given example1 then solvePart2() returns correct value`() {
        val result = solver.solvePart2(example1Lines)
        assertEquals(
            """
                #####
                #...#
                #...#
                #...#
                #####
                .....
                .....
            """.trimIndent(),
            result
        )
    }
}