import java.io.File

fun main(args: Array<String>) {
    println("Program arguments: ${args.joinToString()}")

    fun getIndexFollowingFirstUniqueSubString(length: Int) =
        File("in/problem-6.txt")
            .readText()
            .windowed(size = length, step = 1)
            .map { it.toSet().size }
            .indexOfFirst { it == length } + length

    fun part1() = getIndexFollowingFirstUniqueSubString(4)
    fun part2() = getIndexFollowingFirstUniqueSubString(14)

    println("Part 1: ${part1()}")
    println("Part 2: ${part2()}")
}


