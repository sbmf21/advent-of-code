package nl.sbmf21.aoc22

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc.common.AocBase
import nl.sbmf21.aoc22.days.Day5
import nl.sbmf21.aoc22.days.Day9
import nl.sbmf21.aoc22.days.simulations.Crates
import nl.sbmf21.aoc22.days.simulations.Simulation
import nl.sbmf21.aoc22.days.simulations.Snake

class Aoc : AocBase("2022")

fun main(args: Array<String>) {
    val aoc = Aoc()
    if ("--crates" in args) simulate<Day5, Crates>(aoc) { Crates(it) }
    else if ("--snake" in args) simulate<Day9, Snake>(aoc) { Snake(it) }
    else aoc.exec(args)
}

private inline fun <reified T : ADay, reified S : Simulation<T>> simulate(aoc: Aoc, init: (T) -> S) {
    val day = aoc.days.first { it.clazz == T::class.java }.build() as T
    println("Simulating day ${day.number}")
    init.invoke(day).simulate()
}
