import java.util.regex.Pattern

object Helper {
    private val anyNumberOfSpacesPattern = Pattern.compile("\\s+")

    fun getBoardSize(lines: List<String>): Int =
        lines
            .asSequence()
            .drop(1)
            .first { it.isNotEmpty() }
            .trim()
            .split(anyNumberOfSpacesPattern)
            .size

    fun getDrawnNumbers(lines: List<String>): List<Int> =
        lines
            .first()
            .split(",")
            .mapNotNull { it.toIntOrNull() }

    fun buildBoards(lines: List<String>, boardSize: Int): List<Board> =
        lines
            .asSequence()
            .drop(1)
            .filter { it.isNotEmpty() }
            .chunked(boardSize)
            .map { lineChunk ->
                lineChunk.map { line ->
                    line
                        .trim()
                        .split(anyNumberOfSpacesPattern)
                        .map { rawNumber ->
                            rawNumber.toInt()
                        }
                }
            }
            .map { rows ->
                rows.map { columns ->
                    columns.map { value -> Case(value) }
                }
            }
            .toList()
}