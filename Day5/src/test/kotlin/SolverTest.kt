import org.junit.Test
import java.awt.Point
import kotlin.test.assertEquals

class SolverTest {

    private val example = listOf(
        Segment(Point(0, 9), Point(5, 9)),
        Segment(Point(8, 0), Point(0, 8)),
        Segment(Point(9, 4), Point(3, 4)),
        Segment(Point(2, 2), Point(2, 1)),
        Segment(Point(7, 0), Point(7, 4)),
        Segment(Point(6, 4), Point(2, 0)),
        Segment(Point(0, 9), Point(2, 9)),
        Segment(Point(3, 4), Point(1, 4)),
        Segment(Point(0, 0), Point(8, 8)),
        Segment(Point(5, 5), Point(8, 2))
    )
    private val solver = Solver()

    @Test
    fun `given example then solvePart1() returns correct value`() {
        val result = solver.solvePart1(example)

        assertEquals(5, result)
    }

    @Test
    fun `given example then solvePart2() returns correct value`() {
        val result = solver.solvePart2(example)

        assertEquals(12, result)
    }
}