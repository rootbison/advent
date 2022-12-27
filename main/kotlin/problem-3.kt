import java.io.File

data class Rucksack(val pouch1: String, val pouch2: String) {
    val duplicate: Char = pouch1.toSet().intersect(pouch2.toSet()).single()
    val contents: String = pouch1 + pouch2
}

data class RucksackGroup(val rucksacks: List<Rucksack>) {
    val duplicate: Char = rucksacks
        .map { it.contents.toSet() }
        .reduce { acc, next -> acc.intersect(next) }
        .single()
}

fun main(args: Array<String>) {
    println("Program arguments: ${args.joinToString()}")

    fun getPriority(char: Char): Int {
        // uppercase: 64 - 26
        return if (char.isUpperCase()) char.code - 38
        else char.code - 96
    }

    fun parseInput(): List<Rucksack> {
        return File("in/problem-3.txt")
            .readText()
            .split(NEWLINE_WINDOWS)
            .map { Rucksack(it.substring(0, it.length / 2), it.substring(it.length / 2)) }
    }

    fun part1(): Int {
        val rucksacks = parseInput()
        return rucksacks
            .map { rucksack: Rucksack -> getPriority(rucksack.duplicate) }
            .reduce { acc, score -> acc + score }
    }

    fun part2(): Int {
        val rucksackGroups = parseInput()
            .chunked(3)
            .map { RucksackGroup(it) }
        return rucksackGroups
            .map { rucksackGroup: RucksackGroup -> getPriority(rucksackGroup.duplicate) }
            .reduce { acc, score -> acc + score }
    }

    println("Part 1: ${part1()}")
    println("Part 2: ${part2()}")

}


