package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc.common.mapToInts

class Day4(input: List<String>) : ADay(input) {

    private val regex = Regex("(\\d+)-(\\d+),(\\d+)-(\\d+)")

    override fun part1() = overlappingCount {
        (it[0] >= it[2] && it[1] <= it[3]) || (it[2] >= it[0] && it[3] <= it[1])
    }

    override fun part2() = overlappingCount {
        val (first, second) = Pair(it[0]..it[1], it[2]..it[3])
        it[0] in second || it[1] in second || it[2] in first || it[3] in first
    }

    private fun overlappingCount(overlaps: (numbers: List<Int>) -> Boolean) = input.count {
        overlaps(regex.find(it)!!.destructured.toList().mapToInts())
    }
}
