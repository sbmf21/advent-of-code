package nl.sbmf21.aoc16.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.llength
import nl.sbmf21.aoc.common.mapToInts

class Day9 : Day() {

    private val compressed = input[0]

    override fun part1() = length(compressed, String::llength)

    override fun part2() = length(compressed)

    private fun length(compressed: String, check: (String) -> Long = { length(it) }): Long {
        var next = compressed
        var length = 0L

        while (next.contains('(')) {
            val start = next.indexOf('(')
            val end = next.indexOf(')')
            val (width, times) = next.substring(start + 1..<end).split('x').mapToInts()
            val subEnd = end + width

            length += start + check(next.substring(end + 1..subEnd)) * times
            next = next.substring(subEnd + 1..next.lastIndex)
        }

        return length + next.length
    }
}