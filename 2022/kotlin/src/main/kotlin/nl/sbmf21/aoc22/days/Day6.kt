package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay

class Day6 : ADay() {

    private val dataStream = input[0]

    override fun part1() = findMarker(4)

    override fun part2() = findMarker(14)

    private fun findMarker(length: Int): Int {
        for (i in 0..dataStream.length - length) {
            if (dataStream.substring(i until i + length).toList().distinct().size == length) {
                return i + length
            }
        }
        return -1
    }
}