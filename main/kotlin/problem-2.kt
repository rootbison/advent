import java.io.File

data class Round(val oppoShapeCode: Char, val selfShapeCode: Char? = null, val outcomeCode: Char? = null) {
    private val oppoShape: String = getShapeFromCode(oppoShapeCode) ?: error("bad oppo shape code")
    private val selfShape: String = getShapeFromCode(selfShapeCode) ?: calculateShape()
    private fun getShapeFromCode(shapeCode: Char?): String? = when (shapeCode) {
        'X', 'A' -> "Rock"
        'Y', 'B' -> "Paper"
        'Z', 'C' -> "Scissors"
        else -> null
    }

    private fun getShapeScore(shape: String): Int = when (shape) {
        "Rock" -> 1
        "Paper" -> 2
        "Scissors" -> 3
        else -> error("shapeScore")
    }

    private fun getOutcomeScore(): Int {
        return when(selfShape to oppoShape) {
            "Rock" to "Paper",  "Paper" to "Scissors", "Scissors" to "Rock" -> 0
            "Rock" to "Rock", "Paper" to "Paper", "Scissors" to "Scissors" -> 3
            "Rock" to "Scissors",  "Paper" to "Rock", "Scissors" to "Paper" -> 6
            else -> error("outcomeScore")
        }
    }

    // part 2
    private fun calculateShape(): String {
         val outcome: String = when (outcomeCode) {
            'X' -> "Lose"
            'Y' -> "Draw"
            'Z' -> "Win"
            else -> error("error: $outcomeCode")
        }
        return when(outcome to oppoShape) {
            "Draw" to "Paper",    "Win" to "Rock",     "Lose" to "Scissors"  -> "Paper"
            "Draw" to "Scissors", "Win" to "Paper",    "Lose" to "Rock"      -> "Scissors"
            "Draw" to "Rock",     "Win" to "Scissors", "Lose" to "Paper"     -> "Rock"
            else -> error("error: outcome $outcome, oppoShape $oppoShape")
        }
    }

    fun getScore(): Int = this.getShapeScore(selfShape) + getOutcomeScore()
}
fun main(args: Array<String>) {
    println("Program arguments: ${args.joinToString()}")

    fun parseInput(filepath: String = FILEPATH_DAY_02): List<String> {
        return File(filepath)
            .readText()
            .split(NEWLINE_WINDOWS)
    }

    fun part1(): Int {
        val rounds: List<Round> =  parseInput()
            .map { Round(it[0], selfShapeCode = it[2]) }
        return rounds
            .map { elem: Round -> elem.getScore() }
            .reduce {acc, score -> acc + score}
    }

    fun part2(): Int {
        val rounds: List<Round> =  parseInput()
            .map { Round(it[0], outcomeCode = it[2]) }
        return rounds
            .map { elem: Round -> elem.getScore() }
            .reduce {acc, score -> acc + score}
    }

    println("Part 1: ${part1()}")
    println("Part 2: ${part2()}")
}


