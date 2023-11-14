package nl.sbmf21.aoc22

import nl.sbmf21.aoc.common.AocBase
import nl.sbmf21.aoc.common.buildSimulation
import nl.sbmf21.aoc22.days.simulations.CRT
import nl.sbmf21.aoc22.days.simulations.Crates
import nl.sbmf21.aoc22.days.simulations.LavaDroplet
import nl.sbmf21.aoc22.days.simulations.Snake

class Aoc : AocBase(
    "2022", mapOf(
        "crates" to buildSimulation(::Crates),
        "snake" to buildSimulation(::Snake),
        "crt" to buildSimulation(::CRT),
        "lava" to buildSimulation(::LavaDroplet),
    )
)

fun main(args: Array<String>) = Aoc().exec(args)