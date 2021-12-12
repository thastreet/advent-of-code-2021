class Solver {
    companion object {
        private const val START = "start"
        private const val END = "end"
    }

    fun solvePart1(lines: List<String>): Int {
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

        val paths: List<List<Pair<String, String>>> = navigate(segments)
            .filter { it.isNotEmpty() && it.last().second == END }

        return paths.size
    }

    private fun navigate(segments: Map<String, List<String>>): List<List<Pair<String, String>>> =
        navigate(segments, emptyList())

    private fun navigate(
        segments: Map<String, List<String>>,
        currentPath: List<Pair<String, String>>
    ): List<List<Pair<String, String>>> {
        if (currentPath.isEmpty()) {
            return segments
                .filterKeys {
                    it == START
                }
                .map { entry ->
                    entry.value
                        .map { destination ->
                            val path = Pair(entry.key, destination)
                            navigate(segments, listOf(path))
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
                    val small = name != START && name != END && name.all { char ->
                        char.code in 97..122
                    }

                    val occurrences = currentPath
                        .map {
                            listOf(it.first, it.second)
                        }
                        .flatten()

                    !small || occurrences.count { name == it } < 2
                }
                .map {
                    Pair(currentDestination, it)
                }
                .filter {
                    it !in currentPath
                }

            return if (paths.isNotEmpty()) {
                paths
                    .map {
                        navigate(segments, currentPath + listOf(it))
                    }
                    .flatten()
            } else {
                listOf(currentPath)
            }
        }
    }
}