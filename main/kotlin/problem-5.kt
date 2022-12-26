import java.io.File

data class CrateStack(val crates: ArrayDeque<Char>) {
    // return LIFO-ordered Deque containing removed elements
    fun removeN(size: Int): ArrayDeque<Char> {
        val inFlight: ArrayDeque<Char> = ArrayDeque()
        for (i in 0 until size) {
            inFlight.addLast(crates.removeLast())
        }
        return inFlight
    }

    fun addN(payload: ArrayDeque<Char>, preserveOrder: Boolean = false) {
        for (i in 0 until payload.size) {
            if (preserveOrder) {
                crates.addLast(payload.removeLast())
            } else {
                crates.addLast(payload.removeFirst())
            }
        }
    }

    fun getTopCrate(): Char {
        return crates[crates.size - 1]
    }

}

data class CargoCrane(val stacks: List<CrateStack>) {
    fun getTopCrates(): List<Char> {
        return stacks.map {it.getTopCrate()}
    }

}

data class Move(val input: String) {
    private val components = input.split(" ")
    val size: Int = components[1].toInt()
    val src: Int = components[3].toInt() - 1    // 0-indexed
    val dest: Int = components[5].toInt() - 1   // 0-indexed
}


fun main(args: Array<String>) {
    println("Program arguments: ${args.joinToString()}")

    fun init(): CargoCrane {
        return CargoCrane(
            listOf(
                CrateStack(ArrayDeque(listOf('B', 'Z', 'T'))),
                CrateStack(ArrayDeque(listOf('V', 'H', 'T', 'D', 'N'))),
                CrateStack(ArrayDeque(listOf('B', 'F', 'M', 'D'))),
                CrateStack(ArrayDeque(listOf('T', 'J', 'G', 'W', 'V', 'Q', 'L'))),
                CrateStack(ArrayDeque(listOf('W', 'D', 'G', 'P', 'V', 'F', 'Q', 'M'))),
                CrateStack(ArrayDeque(listOf('V', 'Z', 'Q', 'G', 'H', 'F', 'S'))),
                CrateStack(ArrayDeque(listOf('Z', 'S', 'N', 'R', 'L', 'T', 'C', 'W'))),
                CrateStack(ArrayDeque(listOf('Z', 'H', 'W', 'D', 'J', 'N', 'R', 'M'))),
                CrateStack(ArrayDeque(listOf('M', 'Q', 'L', 'F', 'D', 'S')))
            )
        )
    }

    fun getMoves(): List<Move> {
        return File("in/problem-5.txt")
            .readText()
            .split(NEWLINE_WINDOWS)
            .map { Move(it) }
    }


    fun part1(): String {
        val cargo = init()
        for (move in getMoves()) {
            val inFlightCrates = cargo.stacks[move.src].removeN(move.size)
            println(inFlightCrates)
            cargo.stacks[move.dest].addN(inFlightCrates)
            println("dest: ${cargo.stacks[move.dest]}")
            println("src: ${cargo.stacks[move.src]}")
        }
        return cargo.getTopCrates().joinToString("")
    }

    fun part2(): String {
        return "ABC"
    }

    println("Part 1: ${part1()}")
    println("Part 2: ${part2()}")
}


