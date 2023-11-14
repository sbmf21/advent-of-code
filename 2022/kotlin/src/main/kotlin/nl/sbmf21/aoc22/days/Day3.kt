package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay

class Day3 : ADay() {

    override fun part1() = input.sumOf { items ->
        prio(items.substring(0, items.length / 2).first { items.substring(items.length / 2).contains(it) })
    }

    override fun part2() = input.chunked(3).sumOf { group ->
        prio(group[0].first { group[1].contains(it) && group[2].contains(it) })
    }

    private fun prio(char: Char): Int {
        val prio = char.code and 31
        return if (char.code and 32 == 0) prio + 26
        else prio
    }
}