package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc21.Aoc

typealias Run = (Int) -> Unit

class Day2(aoc: Aoc, number: Int) : ADay(aoc, number) {

    private val actions = input
        .map { it.split(' ') }
        .map { Pair(it[0], it[1].toInt()) }

    override fun part1(): Int {
        var x = 0; var y = 0
        run({ x += it }, { y -= it }, { y += it }).also { return x * y }
    }

    override fun part2(): Int {
        var x = 0; var y = 0; var p = 0
        run({ x += it; y += (p * it) }, { p -= it }, { p += it }).also { return x * y }
    }

    private fun run(forward: Run, up: Run, down: Run) = actions.forEach {
        when (it.first) {
            "forward" -> forward(it.second)
            "up" -> up(it.second)
            "down" -> down(it.second)
        }
    }
}
