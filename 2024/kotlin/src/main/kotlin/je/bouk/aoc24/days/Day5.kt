package je.bouk.aoc24.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.mapToInts
import nl.sbmf21.aoc.common.middle
import nl.sbmf21.aoc.common.split

class Day5 : Day() {

    private val rules: List<Pair<Int, Int>>
    private val updates: List<List<Int>>

    init {
        val (rules, produce) = input.split { it.isBlank() }

        this.rules = rules.map {
            val (left, right) = it.split("|", limit = 2).mapToInts()
            left to right
        }

        this.updates = produce.map { it.split(",").mapToInts() }
    }

    override fun part1() = updates
        .filter(::isValid)
        .sumOf(List<Int>::middle)

    override fun part2() = updates.filterNot(::isValid).map(List<Int>::toMutableList).sumOf { update ->
        check@ while (true) {
            for (li in 0..<update.lastIndex) {
                val left = update[li]
                val ri = li + 1
                val right = update[ri]

                if (missing(left, right)) {
                    update[li] = right
                    update[ri] = left
                    continue@check
                }
            }
            break
        }

        update.middle()
    }

    private fun isValid(ints: List<Int>): Boolean {
        for (li in 0..<ints.lastIndex)
            for (ri in li + 1..ints.lastIndex)
                if (missing(ints[li], ints[ri]))
                    return false
        return true
    }

    private fun missing(left: Int, right: Int) = left to right !in rules
}