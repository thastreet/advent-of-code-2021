import java.io.File

fun main(args: Array<String>) {
    val commands =
        File("input.txt")
            .readLines()
            .mapNotNull { line -> line.split(" ").takeIf { parts -> parts.size == 2 } }
            .map { parts ->
                Command(
                    Direction.values().first { it.name.lowercase() == parts[0] },
                    parts[1].toInt()
                )
            }

    val solver = Solver()

    val part1Result = solver.solvePart1(commands)
    println("Part 1: $part1Result")

    val part2Result = solver.solvePart2(commands)
    println("Part 2: $part2Result")
}