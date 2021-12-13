typealias Path = List<Pair<String, String>>

class Solver {
    companion object {
        private const val START = "start"
        private const val END = "end"
    }

    fun solvePart1(lines: List<String>): Int =
        findNumberOfPaths(lines, false).count()

    fun solvePart2(lines: List<String>): Int {
        val paths = findNumberOfPaths(lines, true)
        val pathsWithOnlyOneMultipleSmallCaves = getPathsWithOnlyOneMultipleSmallCaves(paths)
        return pathsWithOnlyOneMultipleSmallCaves.size
    }

    private fun getPathsWithOnlyOneMultipleSmallCaves(paths: List<Path>): List<Path> =
        paths
            .filter { path ->
                val occurrences = path
                    .map {
                        listOf(it.first, it.second)
                    }
                    .flatten()

                val smallCaves =
                    occurrences
                        .distinct()
                        .filter {
                            it.isSmallCave()
                        }

                val multipleSmallCavesCount =
                    smallCaves
                        .count { smallCave ->
                            occurrences.filter { it == smallCave }.size > 2
                        }

                multipleSmallCavesCount <= 1
            }

    private fun findNumberOfPaths(
        lines: List<String>,
        allowSeveralOccurrences: Boolean
    ): List<Path> {
        val connections =
            lines
                .map {
                    val connection = it.split("-")
                    Pair(connection[0], connection[1])
                }

        val segmentsForward: Map<String, List<String>> =
            connections
                .groupBy {
                    it.first
                }
                .filterKeys { it != END }
                .mapValues { entry ->
                    entry.value
                        .map { connection ->
                            connection.second
                        }
                        .filter { it != START }
                        .distinct()
                }

        val segmentsBackward: Map<String, List<String>> =
            connections
                .groupBy {
                    it.second
                }
                .filterKeys { it != END }
                .mapValues { entry ->
                    entry.value
                        .map { connection ->
                            connection.first
                        }
                        .filter { it != START }
                        .distinct()
                }
                .filterValues { it.isNotEmpty() }

        val segments: Map<String, List<String>> =
            (segmentsForward.keys + segmentsBackward.keys)
                .associateWith {
                    (segmentsForward[it] ?: emptyList()) + (segmentsBackward[it] ?: emptyList())
                }

        return navigate(segments, allowSeveralOccurrences)
            .filter { it.isNotEmpty() && it.last().second == END }
    }

    private fun navigate(
        segments: Map<String, List<String>>,
        allowSeveralOccurrences: Boolean
    ): List<Path> =
        navigate(segments, allowSeveralOccurrences, emptyList())

    private fun navigate(
        segments: Map<String, List<String>>,
        allowSeveralOccurrences: Boolean,
        currentPath: Path
    ): List<Path> {
        if (currentPath.isEmpty()) {
            return segments
                .filterKeys {
                    it == START
                }
                .map { entry ->
                    entry.value
                        .map { destination ->
                            val path = Pair(entry.key, destination)
                            navigate(segments, allowSeveralOccurrences, listOf(path))
                        }
                        .flatten()
                }
                .flatten()
        } else {
            val currentConnection = currentPath.last()
            val currentDestination = currentConnection.second

            if (currentDestination == END) return listOf(currentPath)

            val destinations: List<String> = segments[currentDestination] ?: emptyList()

            val paths = destinations
                .filter { name ->
                    val small = name.isSmallCave()
                    val multipleSmallCavesCount = currentPath.multipleSmallCavesCount()
                    val occurrences = currentPath.getOccurrences()
                    val occurrencesOfName = occurrences.count { it == name }
                    val occurrencesAllowed = if (allowSeveralOccurrences) 4 else 2

                    !small || (occurrencesOfName < occurrencesAllowed && multipleSmallCavesCount <= 1)
                }
                .map {
                    Pair(currentDestination, it)
                }
                .filter { pair ->
                    if (allowSeveralOccurrences) {
                        currentPath.count { it == pair } <= 1
                    } else {
                        pair !in currentPath
                    }
                }

            return if (paths.isNotEmpty()) {
                paths
                    .map {
                        navigate(segments, allowSeveralOccurrences, currentPath + listOf(it))
                    }
                    .flatten()
            } else {
                listOf(currentPath)
            }
        }
    }

    private fun Path.multipleSmallCavesCount(): Int {
        val occurrences = getOccurrences()

        val smallCaves =
            occurrences
                .distinct()
                .filter {
                    it.isSmallCave()
                }

        return smallCaves
            .count { smallCave ->
                occurrences.filter { it == smallCave }.size > 2
            }
    }

    private fun Path.getOccurrences(): List<String> =
        this
            .map {
                listOf(it.first, it.second)
            }
            .flatten()

    private fun String.isSmallCave(): Boolean =
        this != START && this != END && this.all { char ->
            char.code in 97..122
        }
}