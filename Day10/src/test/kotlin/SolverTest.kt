import org.junit.Test
import kotlin.test.assertEquals

class SolverTest {
    private val example = """
        [({(<(())[]>[[{[]{<()<>>
        [(()[<>])]({[<{<<[]>>(
        {([(<{}[<>[]}>{[]{[(<()>
        (((({<>}<{<{<>}{[]{[]{}
        [[<[([]))<([[{}[[()]]]
        [{[{({}]{}}([{[{{{}}([]
        {<[[]]>}<{[{[{[]{()[[[]
        [<(<(<(<{}))><([]([]()
        <{([([[(<>()){}]>(<<{{
        <{([{{}}[<[[[<>{}]]]>[]]
    """.trimIndent()

    private val lines = example.split("\n")

    private val solver = Solver()

    @Test
    fun `given example then solvePart1() returns correct value`() {
        val answer = solver.solvePart1(lines)
        assertEquals(26397, answer)
    }
}