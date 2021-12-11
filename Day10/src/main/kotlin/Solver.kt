import java.util.*

class Solver {
    private val pairs = listOf(
        Pair('(', ')'),
        Pair('[', ']'),
        Pair('{', '}'),
        Pair('<', '>')
    )

    fun solvePart1(lines: List<String>): Int {
        val points = listOf(
            Pair(')', 3),
            Pair(']', 57),
            Pair('}', 1197),
            Pair('>', 25137)
        )

        val opened = pairs.map { it.first }
        val closed = pairs.map { it.second }

        val firstInvalids = mutableListOf<Char>()

        lines
            .forEach { line ->
                val stack = Stack<Char>()

                for (char in line) {
                    if (char in opened) {
                        stack.push(char)
                    } else if (stack.isEmpty()) {
                        firstInvalids.add(char)
                        break
                    } else if (char in closed) {
                        val latest = stack.peek()
                        val matchingOpened = pairs.first {
                            it.second == char
                        }.first

                        if (latest == matchingOpened) {
                            stack.pop()
                        } else {
                            firstInvalids.add(char)
                            break
                        }
                    } else {
                        throw IllegalArgumentException("Unsupported char found: $char")
                    }
                }
            }

        return firstInvalids
            .groupBy { it }
            .mapValues { it.value.size }
            .entries
            .sumOf { entry ->
                val point = points.first { it.first == entry.key }.second
                point * entry.value
            }
    }
}