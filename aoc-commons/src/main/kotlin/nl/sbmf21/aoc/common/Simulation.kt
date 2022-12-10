package nl.sbmf21.aoc.common

// ASCI colors https://www.lihaoyi.com/post/BuildyourownCommandLinewithANSIescapecodes.html

const val GREEN = "\u001b[32m"
const val YELLOW = "\u001b[33m"
const val BLACK = "\u001b[38;5;0m"
const val LIGHT_GREEN = "\u001b[38;5;46m"
const val GRAY = "\u001B[38;5;245m"
const val WHITE = "\u001b[38;5;255m"

const val BACK_DARK_GREEN = "\u001b[48;5;22m"

const val RESET = "\u001b[0m"

fun headers(day: Int, offset: Int): List<String> {
    val before = " ".repeat(offset)
    return listOf(
        "$before$YELLOW\u066D${GREEN}  Advent of Code  $YELLOW\u066D$RESET",
        "$before     ${YELLOW}2022${GREEN} day $YELLOW$day$RESET\n",
    )
}

abstract class Simulation<T : ADay> {
    protected abstract val frames: List<Frame>
    protected var framesCount: Long = 60

    fun simulate() = frames.forEach {
        println("\u001b\u0063")
        it.print()
        Thread.sleep(1000 / framesCount) // 60 fps
    }
}

abstract class Frame {

    protected abstract val content: String

    fun print() = println(content)
}
