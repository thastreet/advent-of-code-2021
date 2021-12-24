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

    @Test
    fun `given example 1 then solvePart2() returns correct value`() {
        val result = solver.solvePart2("C200B40A82")

        assertEquals(3.toBigInteger(), result)
    }

    @Test
    fun `given example 2 then solvePart2() returns correct value`() {
        val result = solver.solvePart2("04005AC33890")

        assertEquals(54.toBigInteger(), result)
    }

    @Test
    fun `given example 3 then solvePart2() returns correct value`() {
        val result = solver.solvePart2("880086C3E88112")

        assertEquals(7.toBigInteger(), result)
    }

    @Test
    fun `given example 4 then solvePart2() returns correct value`() {
        val result = solver.solvePart2("CE00C43D881120")

        assertEquals(9.toBigInteger(), result)
    }

    @Test
    fun `given example 5 then solvePart2() returns correct value`() {
        val result = solver.solvePart2("D8005AC2A8F0")

        assertEquals(1.toBigInteger(), result)
    }

    @Test
    fun `given example 6 then solvePart2() returns correct value`() {
        val result = solver.solvePart2("F600BC2D8F")

        assertEquals(0.toBigInteger(), result)
    }

    @Test
    fun `given example 7 then solvePart2() returns correct value`() {
        val result = solver.solvePart2("9C005AC2F8F0")

        assertEquals(0.toBigInteger(), result)
    }

    @Test
    fun `given example 8 then solvePart2() returns correct value`() {
        val result = solver.solvePart2("9C0141080250320F1802104A08")

        assertEquals(1.toBigInteger(), result)
    }
}