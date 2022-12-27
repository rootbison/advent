import java.io.File

fun main(args: Array<String>) {
    println("Program arguments: ${args.joinToString()}")

    fun buildTree() =
        File(FILEPATH_DAY_07)
            .readText()
            .split(NEWLINE_WINDOWS)

    fun part1(): Unit {}

    println("Part 1: ${part1()}")
//    println("Part 2: ${part2()}")
}
