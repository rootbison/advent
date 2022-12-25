import java.io.File

data class Rucksack(val pouch1: String, val pouch2: String) {
    private val duplicate: Char = pouch1.toSet().intersect(pouch2.toSet()).first()

    fun getPriority(): Int {
        // uppercase: 64 - 26
        return if (duplicate.isUpperCase()) duplicate.code - 38
        else duplicate.code - 96
    }
}
data class RucksackGroup(val pouch1: String, val pouch2: String) {
    private val duplicate: Char = pouch1.toSet().intersect(pouch2.toSet()).first()

    fun getPriority(): Int {
        // uppercase: 64 - 26
        return if (duplicate.isUpperCase()) duplicate.code - 38
        else duplicate.code - 96
    }
}

fun main(args: Array<String>) {
    println("Program arguments: ${args.joinToString()}")

    fun parseInput(): String {
        return File("in/problem-3.txt").readText()
    }

    fun part1(): Int {
        val rucksacks: List<Rucksack> =  parseInput().split(NEWLINE_WINDOWS)
            .map { Rucksack(it.substring(0, it.length / 2), it.substring(it.length / 2)) }
        return rucksacks
            .map { elem: Rucksack -> elem.getPriority() }
            .reduce {acc, score -> acc + score}
    }


    println("Part 1: ${part1()}")
    println("Part 2: ${part2()}")
}


