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
        else -> error("error")
    }

    private fun getOutcomeScore(): Int {
        return if (selfShape === oppoShape) 3
        else if (
            (selfShape === "Rock" && oppoShape === "Paper") ||
            (selfShape === "Paper" && oppoShape === "Scissors") ||
            (selfShape === "Scissors" && oppoShape === "Rock")) {
            0
        } else {
            6
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

//        println("oppo: $oppoShape, self: $selfShape, outcome: $outcomeCode")
        if (outcome === "Draw") {
            return oppoShape
        } else if (outcome === "Win") {
            return when(oppoShape) {
                "Rock" -> "Paper"
                "Paper" -> "Scissors"
                "Scissors" -> "Rock"
                else -> error("error")
            }
        } else {
            return when(oppoShape) {
                "Rock" -> "Scissors"
                "Paper" -> "Rock"
                "Scissors" -> "Paper"
                else -> error("error")
            }
        }
    }

    fun getScore(): Int {
        return this.getShapeScore(selfShape) + getOutcomeScore()
    }
}
fun main(args: Array<String>) {
    println("Program arguments: ${args.joinToString()}")

    operator fun String.component1(): Char = this[0]
    operator fun String.component2(): Char = this[1]
    operator fun String.component3(): Char = this[2]

    fun parseInput(): String {
        return File("in/problem-2.txt").readText()
    }

    fun part1(): Int {
        val rounds: List<Round> =  parseInput().split(NEWLINE_WINDOWS).map { Round(it[0], selfShapeCode = it[2]) }
        return rounds
            .map { elem: Round -> elem.getScore() }
            .reduce {acc, score -> acc + score}
    }

    fun part2(): Int {
        val rounds: List<Round> =  parseInput().split(NEWLINE_WINDOWS).map { Round(it[0], outcomeCode = it[2]) }
        return rounds
            .map { elem: Round -> elem.getScore() }
            .reduce {acc, score -> acc + score}
    }

    println("Part 1: ${part1()}")
    println("Part 2: ${part2()}")

}


