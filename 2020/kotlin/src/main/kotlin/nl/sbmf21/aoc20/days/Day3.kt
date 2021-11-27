package nl.sbmf21.aoc20.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc20.Aoc

class Day3(aoc: Aoc) : ADay(aoc) {

    private val map = input.map { it.toCharArray() }

    override fun number() = 3

    override fun part1() = countTrees(3)

    override fun part2() = listOf(
        countTrees(1),
        part1(),
        countTrees(5),
        countTrees(7),
        countTrees(1, 2)
    ).reduce(Int::times)

    private fun countTrees(yStep: Int, xStep: Int = 1): Int {
        var y = 0
        var count = 0

        for (x in map.indices step xStep) {
            count += countNum(x, y)

            y += yStep
        }

        return count
    }

    private fun countNum(x: Int, y: Int) = when (getChar(x, y)) {
        '#' -> 1
        else -> 0
    }

    private fun getChar(x: Int, y: Int) = map[x][y % map[x].size]
}
