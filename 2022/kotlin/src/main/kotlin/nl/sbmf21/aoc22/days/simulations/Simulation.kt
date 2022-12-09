package nl.sbmf21.aoc22.days.simulations

import nl.sbmf21.aoc.common.ADay

internal const val GREEN = "\u001b[32m"
internal const val YELLOW = "\u001b[33m"
internal const val GRAY = "\u001B[38;5;245m"
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

    fun simulate() = frames.forEach {
        println("\u001b\u0063")
        it.print()
        Thread.sleep(1000 / 60) // 60 fps
    }
}

internal interface Frame {
    fun print()
}
