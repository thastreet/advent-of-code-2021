import java.util.*

class Solver {
    private val pairs = listOf(
        Pair('(', ')'),
        Pair('[', ']'),
        Pair('{', '}'),
        Pair('<', '>')
    )

    private val opened = pairs.map { it.first }
    private val closed = pairs.map { it.second }

    fun solvePart1(lines: List<String>): Int {
        val points = listOf(
            Pair(')', 3),
            Pair(']', 57),
            Pair('}', 1197),
            Pair('>', 25137)
        )

        val invalidFirstChars: List<Pair<Int, Char?>> = findInvalidFirstChars(lines)
        val invalidLinesIndex: List<Int> = getInvalidLinesIndex(invalidFirstChars)

        return invalidFirstChars
            .filterIndexed { index, _ -> index in invalidLinesIndex }
            .groupBy { it }
            .mapValues { it.value.size }
            .entries
            .sumOf { entry ->
                val invalidFirstChar = entry.key.second
                val point = points.first { charToPoint -> charToPoint.first == invalidFirstChar }.second
                point * entry.value
            }
    }

    fun solvePart2(lines: List<String>): Long {
        val invalidFirstChars: List<Pair<Int, Char?>> = findInvalidFirstChars(lines)
        val validLinesIndex: List<Int> = getValidLinesIndex(invalidFirstChars)

        val matchingClosed = lines
            .filterIndexed { index, _ -> index in validLinesIndex }
            .map { line ->
                val stack = buildIncompleteStack(line)
                matchClosed(stack)
            }

        val scores = calculateScores(matchingClosed)
        return scores[scores.size / 2]
    }

    private fun findInvalidFirstChars(lines: List<String>): List<Pair<Int, Char?>> =
        lines
            .mapIndexed { index, line ->
                val stack = Stack<Char>()
                var firstInvalid: Char? = null

                for (char in line) {
                    if (char in opened) {
                        stack.push(char)
                    } else if (stack.isEmpty()) {
                        firstInvalid = char
                        break
                    } else if (char in closed) {
                        val latest = stack.peek()
                        if (latest == getMatchingOpened(char)) {
                            stack.pop()
                        } else {
                            firstInvalid = char
                            break
                        }
                    }
                }

                Pair(index, firstInvalid)
            }

    private fun buildIncompleteStack(line: String): Stack<Char> {
        val stack = Stack<Char>()

        for (char in line) {
            if (char in opened) {
                stack.push(char)
            } else if (char in closed) {
                val latest = stack.peek()
                if (latest == getMatchingOpened(char)) {
                    stack.pop()
                } else {
                    break
                }
            }
        }

        return stack
    }

    private fun matchClosed(stack: Stack<Char>): List<Char> {
        val matchingClosed = mutableListOf<Char>()
        while (stack.isNotEmpty()) {
            val current = stack.pop()
            matchingClosed.add(getMatchingClosed(current))
        }
        return matchingClosed
    }

    private fun calculateScores(matchingClosed: List<List<Char>>): List<Long> {
        val points = listOf(
            Pair(')', 1),
            Pair(']', 2),
            Pair('}', 3),
            Pair('>', 4)
        )
        return matchingClosed
            .map {
                it.fold(0L) { acc, char ->
                    acc * 5 + points.first { charToPoint -> charToPoint.first == char }.second
                }
            }
            .sorted()
    }

    private fun getMatchingOpened(char: Char): Char =
        pairs.first {
            it.second == char
        }.first

    private fun getMatchingClosed(char: Char): Char =
        pairs.first {
            it.first == char
        }.second

    private fun getInvalidLinesIndex(invalidFirstChars: List<Pair<Int, Char?>>): List<Int> =
        invalidFirstChars
            .filter { it.second != null }
            .map {
                it.first
            }

    private fun getValidLinesIndex(invalidFirstChars: List<Pair<Int, Char?>>): List<Int> =
        invalidFirstChars
            .filter { it.second == null }
            .map {
                it.first
            }
}