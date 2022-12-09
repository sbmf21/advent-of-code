package nl.sbmf21.aoc22

import nl.sbmf21.aoc.common.AocBase
import nl.sbmf21.aoc22.days.Day9
import nl.sbmf21.aoc22.days.simulations.Snake

class Aoc : AocBase("2022")

fun main(args: Array<String>) {
    val aoc = Aoc()
    if ("--simulate" in args) {
        println("Simulating day 9")
        Snake(aoc.days[8].build() as Day9).runSnake()
    } else aoc.exec(args)
}
