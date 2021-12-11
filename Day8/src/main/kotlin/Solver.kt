class Solver {
    fun solvePart1(lines: List<Line>): Int {
        val uniqueNumbers = setOf(2, 3, 4, 7)
        return lines.sumOf {
            it.outputValue
                .count { value ->
                    value.length in uniqueNumbers
                }
        }
    }

    fun solvePart2(lines: List<Line>): Int =
        lines
            .sumOf { line ->
                val display = createDisplay(line)

                val stringValue: String =
                    line.outputValue
                        .fold("") { acc, value ->
                            acc + display.toInt(value)
                        }
                stringValue.toInt()
            }

    private fun createDisplay(line: Line): Display {
        var display = Display()

        val top = line.findTop()
        display = display.copy(top = top)

        val possibleTopLeftAndMiddle = line.findPossibleTopLeftAndMiddle(top)

        val bottom = line.findBottom(top, possibleTopLeftAndMiddle)
        display = display.copy(bottom = bottom)

        val bottomLeft = line.findBottomLeft(top, bottom, possibleTopLeftAndMiddle)
        display = display.copy(bottomLeft = bottomLeft)

        val bottomRight = line.findBottomRight(top, bottom, bottomLeft, possibleTopLeftAndMiddle)
        display = display.copy(bottomRight = bottomRight)

        val topRight = line.findTopRight(bottomRight)
        display = display.copy(topRight = topRight)

        val middle = line.findMiddle(top, bottom, bottomLeft, topRight)
        display = display.copy(middle = middle)

        val topLeft = findTopLeft(middle, possibleTopLeftAndMiddle)
        display = display.copy(topLeft = topLeft)

        return display
    }

    private fun Line.findTop(): Char {
        val two = findFirstOfLength(2)
        val three = findFirstOfLength(3)
        return three.toCharArray().first { it !in two }
    }

    private fun Line.findPossibleTopLeftAndMiddle(top: Char): String {
        val two = findFirstOfLength(2)
        val four = findFirstOfLength(4)
        return four.filter { it != top && it !in two }
    }

    private fun Line.findBottom(top: Char, possibleTopLeftAndMiddle: String): Char {
        val two = findFirstOfLength(2)
        val sixes = findAllOfLength(6)

        val knownToFindBottom = top + possibleTopLeftAndMiddle + two
        val bottomPredicate: (Char) -> Boolean = {
            it !in knownToFindBottom
        }
        return sixes
            .first { six ->
                six.count(bottomPredicate) == 1
            }
            .first(bottomPredicate)
    }

    private fun Line.findBottomLeft(top: Char, bottom: Char, possibleTopLeftAndMiddle: String): Char {
        val two = findFirstOfLength(2)
        val seven = findFirstOfLength(7)

        val knownToFindBottomLeft = top + possibleTopLeftAndMiddle + two + bottom
        val bottomLeftPredicate: (Char) -> Boolean = {
            it !in knownToFindBottomLeft
        }
        return seven
            .first(bottomLeftPredicate)
    }

    private fun Line.findBottomRight(
        top: Char,
        bottom: Char,
        bottomLeft: Char,
        possibleTopLeftAndMiddle: String
    ): Char {
        val sixes = findAllOfLength(6)

        val knownToFindBottomRight = top + possibleTopLeftAndMiddle + bottom + bottomLeft
        val bottomRightPredicate: (Char) -> Boolean = {
            it !in knownToFindBottomRight
        }
        return sixes
            .first { six ->
                six.count(bottomRightPredicate) == 1
            }
            .first(bottomRightPredicate)
    }

    private fun Line.findTopRight(bottomRight: Char): Char {
        val two = findFirstOfLength(2)
        return two
            .first { it != bottomRight }
    }

    private fun Line.findMiddle(
        top: Char,
        bottom: Char,
        bottomLeft: Char,
        topRight: Char
    ): Char {
        val fives = findAllOfLength(5)

        val knownToFindMiddle = String(listOf(top, bottom, topRight, bottomLeft).toCharArray())
        val middlePredicate: (Char) -> Boolean = {
            it !in knownToFindMiddle
        }
        return fives
            .first { five ->
                five.count(middlePredicate) == 1
            }
            .first(middlePredicate)
    }

    private fun findTopLeft(middle: Char, possibleTopLeftAndMiddle: String): Char =
        possibleTopLeftAndMiddle
            .first { it != middle }

    private fun Line.findFirstOfLength(length: Int): String =
        signalPatterns.first { it.length == length }

    private fun Line.findAllOfLength(length: Int): List<String> =
        signalPatterns.filter { it.length == length }
}

private data class Display(
    val top: Char? = null,
    val topLeft: Char? = null,
    val middle: Char? = null,
    val bottomLeft: Char? = null,
    val bottom: Char? = null,
    val bottomRight: Char? = null,
    val topRight: Char? = null
) {
    private val zero = setOf(
        top, topLeft, topRight, bottomRight, bottom, bottomLeft
    )

    private val two = setOf(
        top, topRight, middle, bottomLeft, bottom
    )

    private val three = setOf(
        top, topRight, middle, bottomRight, bottom
    )

    private val five = setOf(
        top, topLeft, middle, bottomRight, bottom
    )

    private val six = setOf(
        top, topLeft, middle, bottomRight, bottom, bottomLeft
    )

    private val nine = setOf(
        top, topLeft, topRight, middle, bottomRight, bottom
    )

    fun toInt(value: String): Int {
        val valueSet = value.toCharArray().toSet()
        return when (value.length) {
            2 -> 1
            3 -> 7
            4 -> 4
            5 -> {
                when {
                    valueSet.containsAll(two) -> 2
                    valueSet.containsAll(three) -> 3
                    valueSet.containsAll(five) -> 5
                    else -> throw IllegalArgumentException("Unable to map int")
                }
            }
            6 -> {
                when {
                    valueSet.containsAll(zero) -> 0
                    valueSet.containsAll(six) -> 6
                    valueSet.containsAll(nine) -> 9
                    else -> throw IllegalArgumentException("Unable to map int")
                }
            }
            7 -> 8
            else -> throw IllegalArgumentException("Unable to map int")
        }
    }
}