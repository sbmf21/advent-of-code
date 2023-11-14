package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.mapToInts
import nl.sbmf21.aoc.common.triangular
import kotlin.math.abs

class Day7 : Day() {

    private val crabs = input[0].split(",").mapToInts().sorted()
    private val crabRange = crabs.minOf { it }..crabs.maxOf { it }

    override fun part1() = run()

    override fun part2() = run { it.triangular() }

    private fun run(c: (i: Int) -> Int = { it }): Int {
        var m = -1; for (i in crabRange) {
            val f = crabs.sumOf { c(abs(it - i)) }
            if (f < m || m == -1) m = f
        }; return m
    }
}