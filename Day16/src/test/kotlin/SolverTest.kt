import org.junit.Test
import kotlin.test.assertEquals

class SolverTest {
    private val solver = Solver()

    @Test
    fun `given example 1 then solvePart1() returns correct value`() {
        val result = solver.solvePart1("8A004A801A8002F478")

        assertEquals(16, result)
    }

    @Test
    fun `given example 2 then solvePart1() returns correct value`() {
        val result = solver.solvePart1("620080001611562C8802118E34")

        assertEquals(12, result)
    }

    @Test
    fun `given example 3 then solvePart1() returns correct value`() {
        val result = solver.solvePart1("C0015000016115A2E0802F182340")

        assertEquals(23, result)
    }

    @Test
    fun `given example 4 then solvePart1() returns correct value`() {
        val result = solver.solvePart1("A0016C880162017C3686B18A3D4780")

        assertEquals(31, result)
    }
}