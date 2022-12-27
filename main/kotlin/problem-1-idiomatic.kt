import java.io.File

fun main(args: Array<String>) {

    fun parseInput(filepath: String = FILEPATH_DAY_01): List<List<Int>> {
        return File(filepath)
            .readText()
            .split(DOUBLE_NEWLINE_WINDOWS)
            .map { it: String -> it.lines().map { it.toInt() } }
    }

    fun part1(): Int {
        return parseInput().maxOf { it.sum() }
    }

    fun part2(): Int {
        return parseInput()
            .map { it.sum() }
            .sorted()
            .takeLast(3)
            .sum()
    }

    println(part1())
    println(part2())

}
