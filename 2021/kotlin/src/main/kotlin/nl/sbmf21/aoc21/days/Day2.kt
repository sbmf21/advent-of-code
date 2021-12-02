package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc21.Aoc

class Day2(aoc: Aoc, number: Int) : ADay(aoc, number) {

    private val actions = input
        .map { it.split(' ') }
        .map { Pair(it[0], it[1].toInt()) }

    override fun part1(): Int {

        var x = 0
        var y = 0

        run(
            forward = { x += it },
            up = { y -= it },
            down = { y += it }
        )

        return x * y
    }

    override fun part2(): Int {

        var x = 0
        var y = 0
        var p = 0

        run(
            forward = { x += it; y += (p * it) },
            up = { p -= it },
            down = { p += it }
        )

        return x * y
    }

    private fun run(forward: (Int) -> Unit, up: (Int) -> Unit, down: (Int) -> Unit) = actions.forEach {
        when (it.first) {
            "forward" -> forward(it.second)
            "up" -> up(it.second)
            "down" -> down(it.second)
        }
    }
}
