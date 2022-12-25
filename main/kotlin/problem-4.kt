import java.io.File
import kotlin.math.min
import kotlin.math.max

data class TaskComparison(val input: String) {
    private val task1: IntRange = getRange(input.split(',').first())
    private val task2: IntRange = getRange(input.split(',').last())

    private fun getRange(str: String): IntRange {
        val result = str.split('-').map { it.toInt()}
        return result.first() .. result.last()
    }

    fun fullyContains(): Boolean {
        val lowerLowerBound = min(task1.first, task2.first)
        val higherUpperBound = max(task1.last, task2.last)
        return ((lowerLowerBound == task1.first && higherUpperBound == task1.last) ||
            (lowerLowerBound == task2.first && higherUpperBound == task2.last))
    }

//    fun fullyContains(): Boolean {
//        return (task1 - task2).isEmpty() || (task2 - task1).isEmpty()
//    }

    fun hasOverlap(): Boolean {
        return task1.intersect(task2).isNotEmpty()
    }
}


fun main(args: Array<String>) {
    println("Program arguments: ${args.joinToString()}")

    val taskComparisons: List<TaskComparison> = File("in/problem-4.txt")
        .readText()
        .split(NEWLINE_WINDOWS)
        .map { TaskComparison(it) }

    fun part1(): Int {
        return taskComparisons
            .filter { elem: TaskComparison -> elem.fullyContains() }
            .size
    }

    fun part2(): Int {
        return taskComparisons
            .filter { elem: TaskComparison -> elem.hasOverlap() }
            .size
    }

    println("Part 1: ${part1()}")
    println("Part 2: ${part2()}")
}


