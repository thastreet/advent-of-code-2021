class Solver {
    fun solvePart1(drawnNumbers: List<Int>, boards: List<Board>, boardSize: Int): Int {
        val firstWinner = getFirstWinner(drawnNumbers, boards, boardSize)
        return solve(firstWinner)
    }

    fun solvePart2(drawnNumbers: List<Int>, boards: List<Board>, boardSize: Int): Int {
        val lastWinner = getLastWinner(drawnNumbers, boards, boardSize)
        return solve(lastWinner)
    }

    private fun solve(board: Board): Int {
        val notDrawnCases =
            board
                .flatMap { row ->
                    row.filter { case ->
                        !case.isDrawn
                    }
                }

        val latestDrawn: Case =
            board
                .flatten()
                .maxByOrNull { it.drawnIndex ?: 0 }
                ?: throw IllegalStateException("Cannot find latest drawn case")

        val sum = notDrawnCases.sumOf { it.value }
        return latestDrawn.value * sum
    }

    private fun getFirstWinner(drawnNumbers: List<Int>, boards: List<Board>, boardSize: Int): Board {
        for (drawnNumberIndex in drawnNumbers.indices) {
            val drawnNumber = drawnNumbers[drawnNumberIndex]

            boards.forEach { board ->
                markNumber(board, drawnNumber, drawnNumberIndex)

                if (board.isWinner(boardSize)) {
                    return board
                }
            }
        }

        throw IllegalStateException("No winner found")
    }

    private fun getLastWinner(drawnNumbers: List<Int>, boards: List<Board>, boardSize: Int): Board {
        for (drawnNumberIndex in drawnNumbers.indices) {
            val drawnNumber = drawnNumbers[drawnNumberIndex]

            boards.forEach { board ->
                markNumber(board, drawnNumber, drawnNumberIndex)

                if (boards.all { it.isWinner(boardSize) }) return board
            }
        }

        throw IllegalStateException("No winner found")
    }

    private fun markNumber(board: Board, drawnNumber: Int, drawnIndex: Int) {
        board.forEach { row ->
            row.forEach { case ->
                if (case.value == drawnNumber) {
                    case.drawnIndex = drawnIndex
                    return
                }
            }
        }
    }

    private fun Board.isWinner(boardSize: Int): Boolean {
        val anyRowWinner = any { row ->
            row.all { case -> case.isDrawn }
        }

        if (anyRowWinner) return true

        val anyColumnWinner = (0 until boardSize)
            .map { columnIndex ->
                map { row ->
                    row[columnIndex]
                }
            }
            .any { column ->
                column.all { case -> case.isDrawn }
            }

        if (anyColumnWinner) return true

        return false
    }
}