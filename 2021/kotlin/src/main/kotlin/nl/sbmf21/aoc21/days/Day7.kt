package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc.common.mapToInts

class Day7(input: List<String>) : ADay(input) {

    private val crabs = input[0].split(",").mapToInts().sorted()
    private val crabRange = crabs.minOf { it }..crabs.maxOf { it }

    override fun part1() = run()

    override fun part2() = run { it * (it + 1) / 2 }

    private fun run(c: (i: Int) -> Int = { it }): Int {
        var m = -1
        for (i in crabRange) {
            val f = crabs.fold(0) { fuel, it -> fuel + c(if (it < i) i - it else it - i) }
            if (m == -1 || f < m) m = f
        }
        return m
    }
}
