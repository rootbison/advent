import java.io.File

const val NEWLINE_WINDOWS: String = "\r\n\r\n"
fun main(args: Array<String>) {
    val input: String = File("in/problem-1.txt").readText()
    val data: List<List<Int>> = input.split(NEWLINE_WINDOWS).map { it: String -> it.lines().map { it.toInt() } }
    val max: Int = data.maxOf { it.sum()}
    println(max)
}