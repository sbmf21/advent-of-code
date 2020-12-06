package io.frutsel_.aoc.days

import io.frutsel_.aoc.Aoc
import io.frutsel_.aoc.common.ADay

class Day3(aoc: Aoc) : ADay(aoc) {

    private val map = input.map { it.toCharArray() }

    override fun number(): Int = 3

    override fun part1(): Int = countTrees(3)

    override fun part2(): Int = listOf(
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

    private fun countNum(x: Int, y: Int): Int = when (getChar(x, y)) {
        '#' -> 1
        else -> 0
    }

    private fun getChar(x: Int, y: Int): Char = map[x][y % map[x].size]
}
