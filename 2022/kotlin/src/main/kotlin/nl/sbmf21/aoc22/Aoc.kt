package nl.sbmf21.aoc22

import nl.sbmf21.aoc.common.AocBase
import nl.sbmf21.aoc.common.simulate
import nl.sbmf21.aoc22.days.simulations.CRT
import nl.sbmf21.aoc22.days.simulations.Crates
import nl.sbmf21.aoc22.days.simulations.LavaDroplet
import nl.sbmf21.aoc22.days.simulations.Snake

class Aoc : AocBase(
    "2022", mapOf(
        "crates" to simulate(::Crates),
        "snake" to simulate(::Snake),
        "crt" to simulate(::CRT),
        "lava" to simulate(::LavaDroplet),
    )
)

fun main(args: Array<String>) = Aoc().exec(args)