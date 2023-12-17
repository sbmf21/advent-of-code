package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.split
import nl.sbmf21.aoc.common.transpose
import nl.sbmf21.math.Vector2i
import nl.sbmf21.math.by

class Day13 : Day() {

    override fun part1() = solve(0)
    override fun part2() = solve(1)

    private fun solve(differ: Int) = input.split(true, String::isEmpty).sumOf {
        summarize(findFold(it, differ) by findFold(it.transpose(), differ))
    }

    private fun findFold(pattern: List<String>, differ: Int): Int {
        for (scanLine in 0 until pattern[0].lastIndex) {
            var difference = 0
            for (current in 0..scanLine) {
                val reflection = (scanLine - current) + scanLine + 1
                if (reflection !in pattern[0].indices) continue
                difference += pattern.indices.count { pattern[it][current] != pattern[it][reflection] }
            }
            if (difference == differ) return scanLine + 1
        }

        return 0
    }

    private fun summarize(pos: Vector2i) = pos.x + 100 * pos.y
}