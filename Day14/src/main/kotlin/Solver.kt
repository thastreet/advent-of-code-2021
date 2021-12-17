import kotlin.math.max

class Solver {
    fun solvePart1(lines: List<String>): Long =
        solve(lines, 10)

    fun solvePart2(lines: List<String>): Long =
        solve(lines, 40)

    private fun solve(lines: List<String>, steps: Int): Long {
        val rules: Map<String, String> =
            lines.drop(2)
                .associate { line ->
                    val parts = line.split(" -> ")
                    Pair(parts[0], parts[1])
                }

        val windowed = lines.first().windowed(2, 1)

        val initialPairsCount = windowed
            .groupBy { it }
            .mapValues { it.value.size.toLong() }

        val resultPairsCount = (0 until steps)
            .fold(initialPairsCount) { acc, _ ->
                getNewPairsCount(acc, rules)
            }

        val elements =
            resultPairsCount.entries
                .flatMap { it.key.toCharArray().toList() }
                .distinct()

        val elementsOccurrences =
            elements.map { element -> Pair(element, getElementOccurrences(element, resultPairsCount)) }

        val mostCommonCount = elementsOccurrences.maxOf { it.second }
        val leastCommonCount = elementsOccurrences.minOf { it.second }

        return mostCommonCount - leastCommonCount
    }

    private fun getNewPairsCount(pairsCount: Map<String, Long>, rules: Map<String, String>): Map<String, Long> {
        val newPairsCount = mutableMapOf<String, Long>()

        pairsCount.forEach {
            val elementToInsert = rules[it.key]

            val firstPair = it.key[0].toString() + elementToInsert
            val existingFirstPairCount = newPairsCount[firstPair] ?: 0
            newPairsCount[firstPair] = it.value + existingFirstPairCount

            val secondPair = elementToInsert + it.key[1].toString()
            val existingSecondPairCount = newPairsCount[secondPair] ?: 0
            newPairsCount[secondPair] = it.value + existingSecondPairCount
        }

        return newPairsCount
    }

    private fun getElementOccurrences(element: Char, countAfterSteps: Map<String, Long>): Long {
        val keysOccurrences = countAfterSteps.entries
            .filter {
                it.key.contains(element)
            }

        val singleOccurrencesLeft = keysOccurrences
            .filter { it.key.count { keyChar -> keyChar == element } == 1 && it.key[0] == element }
            .sumOf { it.value }

        val singleOccurrencesRight = keysOccurrences
            .filter { it.key.count { keyChar -> keyChar == element } == 1 && it.key[1] == element }
            .sumOf { it.value }

        val singleOccurrences = max(singleOccurrencesLeft, singleOccurrencesRight)

        val doubleOccurrences =
            keysOccurrences
                .filter { it.key.count { keyChar -> keyChar == element } == 2 }.sumOf { it.value }

        return singleOccurrences + doubleOccurrences
    }
}