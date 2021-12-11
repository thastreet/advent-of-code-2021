object Helper {
    fun parseLines(input: List<String>): List<Line> =
        input
            .map {
                val lineParts = it.split("|").map { part -> part.trim() }
                Line(lineParts[0].split(" "), lineParts[1].split(" "))
            }
}