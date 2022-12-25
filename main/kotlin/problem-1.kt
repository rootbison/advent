import java.io.File

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    val fileAsList: List<String> = readFile("in/problem-1.txt")
    val listOfLists: List<List<Int>> = getBags(fileAsList)
    val reduced: List<Int> = reduceLists(listOfLists)
    println(reduced.maxOrNull() ?: 0)
}

fun readFile(fileName: String): List<String>
        = File(fileName).useLines { it.toList() }

fun getBags(unparsedList: List<String>): List<List<Int>> {
    val result: MutableList<MutableList<Int>> = mutableListOf()
    var current: MutableList<Int> = mutableListOf()
    for (item in unparsedList) {
        if (item.isNotEmpty()) {
            try {
                current.add(item.toInt())
            } catch (nfe: NumberFormatException) {
                // not a valid int
            }
        } else {
            result.add(current)
            current = mutableListOf()
        }
    }
    return result
}

fun reduceLists(listOfLists: List<List<Int>>): List<Int> {
    val result: MutableList<Int> = mutableListOf()
    for (list in listOfLists) {
        result.add(list.reduce { acc, elem -> acc + elem })
    }
    return result
}