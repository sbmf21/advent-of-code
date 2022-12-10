package nl.sbmf21.aoc22.days.simulations

import nl.sbmf21.aoc.common.ADay

// ASCI colors https://www.lihaoyi.com/post/BuildyourownCommandLinewithANSIescapecodes.html

internal const val GREEN = "\u001b[32m"
internal const val YELLOW = "\u001b[33m"
internal const val BLACK = "\u001b[38;5;0m"
internal const val GRAY = "\u001B[38;5;245m"
internal const val WHITE = "\u001b[38;5;255m"

internal const val BACK_WHITE = "\u001b[48;5;255m"

internal const val RESET = "\u001b[0m"

internal fun headers(day: Int, offset: Int): List<String> {
    val before = " ".repeat(offset)
    return listOf(
        "$before$YELLOW\u066D  ${GREEN}Advent of Code  $YELLOW\u066D$RESET",
        "$before     ${YELLOW}2022 ${GREEN}day $YELLOW$day$RESET\n",
    )
}

abstract class Simulation<T : ADay> {
    internal abstract val frames: List<Frame>
    internal var framesCount: Long = 60

    fun simulate() = frames.forEach {
        println("\u001b\u0063")
        it.print()
        Thread.sleep(1000 / framesCount) // 60 fps
    }
}

internal interface Frame {
    fun print()
}
