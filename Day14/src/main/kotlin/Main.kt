import java.io.File

fun main(args: Array<String>) {
    val file = File("input.txt")
    val lines = file.readLines()

    val rules: Map<String, String> =
        lines.drop(2)
            .associate { line ->
                val parts = line.split(" -> ")
                Pair(parts[0], parts[1])
            }

    val result = doStep(lines, rules, 10)

    val mostCommonCount =
        result
            .groupBy { it }
            .maxOf { it.value.size }

    val leastCommonCount =
        result
            .groupBy { it }
            .minOf { it.value.size }

    val part1Result = mostCommonCount - leastCommonCount
    println("Part 1 result: $part1Result")
}

private fun doStep(lines: List<String>, rules: Map<String, String>, count: Int): String =
    (0 until count).fold(lines.first()) { result, _ ->
        val toInsert =
            result.windowed(2, 1)
                .mapIndexed { index, s ->
                    Pair(index + 1, rules.getValue(s))
                }

        result.foldIndexed("") { index, acc, char ->
            acc + (toInsert.firstOrNull { it.first == index }?.second ?: "") + char
        }
    }
