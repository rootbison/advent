import java.io.File

const val FILEPATH: String = "in/problem-3.txt"

data class Rucksack(val pouch1: String, val pouch2: String) {
    val duplicate: Char = (pouch1 intersect pouch2).single()
    val contents: String = pouch1 + pouch2
}

data class RucksackGroup(val rucksacks: List<Rucksack>) {
    val duplicate: Char = rucksacks
        .map { it.contents.toSet() }
        .reduce { acc, next -> acc.intersect(next) }
        .single()
}

fun getPriority(char: Char): Int {
    // uppercase: 64 - 26
    return if (char.isUpperCase()) char.code - 38
    else char.code - 96
}

fun parseInput(filepath: String): List<Rucksack> {
    return File(filepath)
        .readText()
        .split(NEWLINE_WINDOWS)
        .map { Rucksack(it.substring(0, it.length / 2), it.substring(it.length / 2)) }
}

fun part1(): Int {
    val rucksacks = parseInput(FILEPATH)
    return rucksacks
        .map { rucksack: Rucksack -> getPriority(rucksack.duplicate) }
        .reduce { acc, score -> acc + score }
}

fun part2(): Int {
    val rucksackGroups = parseInput(FILEPATH)
        .chunked(3)
        .map { RucksackGroup(it) }
    return rucksackGroups
        .map { rucksackGroup: RucksackGroup -> getPriority(rucksackGroup.duplicate) }
        .reduce { acc, score -> acc + score }
}

fun main(args: Array<String>) {
    println("Program arguments: ${args.joinToString()}")

    println("Part 1: ${part1()}")
    println("Part 2: ${part2()}")
}

infix fun String.intersect(that: String) = this.toSet().intersect(that.toSet())

