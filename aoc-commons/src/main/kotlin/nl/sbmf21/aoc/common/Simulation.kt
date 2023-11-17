package nl.sbmf21.aoc.common

import nl.sbmf21.aoc.common.Color.*

abstract class Simulation<T : Puzzle> {
    protected abstract val frames: List<Frame>
    protected var framesCount: Long = 60

    open fun simulate() = frames.forEach {
        println("\u001b\u0063")
        it.print()
        Thread.sleep(1000 / framesCount) // 60 fps
    }

    companion object {
        fun headers(day: Int, offset: Int): List<String> {
            val before = " ".repeat(offset)
            return listOf(
                "$before$YELLOW\u066D$GREEN  Advent of Code  $YELLOW\u066D$RESET",
                "$before     ${YELLOW}2022$GREEN day $YELLOW$day$RESET\n",
            )
        }
    }
}