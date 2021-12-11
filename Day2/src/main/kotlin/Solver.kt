class Solver {
    fun solvePart1(commands: List<Command>): Int {
        val depth = commands.fold(0) { acc, command ->
            acc + when (command.direction) {
                Direction.FORWARD -> 0
                Direction.DOWN -> command.value
                Direction.UP -> -command.value
            }
        }

        val position = commands.fold(0) { acc, command ->
            acc + if (command.direction == Direction.FORWARD) command.value else 0
        }

        return depth * position
    }

    fun solvePart2(commands: List<Command>): Int {
        val result = commands.fold(State()) { acc, command ->
            when (command.direction) {
                Direction.FORWARD -> acc.copy(
                    depth = acc.depth + acc.aim * command.value,
                    position = acc.position + command.value
                )
                Direction.DOWN -> acc.copy(
                    aim = acc.aim + command.value
                )
                Direction.UP -> acc.copy(
                    aim = acc.aim - command.value
                )
            }
        }

        return result.depth * result.position
    }

    private data class State(
        val depth: Int = 0,
        val position: Int = 0,
        val aim: Int = 0
    )
}