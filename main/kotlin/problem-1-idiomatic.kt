import java.io.File

const val NEWLINE_WINDOWS: String = "\r\n\r\n"
fun main(args: Array<String>) {
    val input: String = File("in/problem-1.txt").readText()
    val data: List<Int> = input.split(NEWLINE_WINDOWS).map { it: String -> it.lines().sumOf { it.toInt() } }
    val max: Int = data.max()
    println(max)
}