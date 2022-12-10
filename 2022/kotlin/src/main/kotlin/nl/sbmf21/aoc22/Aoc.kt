package nl.sbmf21.aoc22

import nl.sbmf21.aoc.common.AocBase
import nl.sbmf21.aoc.common.buildSimulation
import nl.sbmf21.aoc22.days.Day10
import nl.sbmf21.aoc22.days.Day5
import nl.sbmf21.aoc22.days.Day9
import nl.sbmf21.aoc22.days.simulations.CRT
import nl.sbmf21.aoc22.days.simulations.Crates
import nl.sbmf21.aoc22.days.simulations.Snake

class Aoc : AocBase(
    "2022", mapOf(
        "crates" to buildSimulation<Day5, Crates> { Crates(it) },
        "snake" to buildSimulation<Day9, Snake> { Snake(it) },
        "crt" to buildSimulation<Day10, CRT> { CRT(it) },
    )
)

fun main(args: Array<String>) = Aoc().exec(args)
