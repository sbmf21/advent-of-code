package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.math.by
import kotlin.math.abs

class Day11 : Day() {

    private val expandedCols = input[0].indices.filter { x -> input.map { it[x] }.all { it == '.' } }
    private val expandedRows = input.indices.filter { y -> input[y].all { it == '.' } }

    override fun part1() = solve()
    override fun part2() = solve(999999L)

    private fun solve(expansion: Long = 1): Long {
        val galaxies = buildList {
            input.forEachIndexed { y, row ->
                row.withIndex().filter { it.value == '#' }.forEach { (x) ->
                    val xOffset = expandedCols.count { it < x } * expansion
                    val yOffset = expandedRows.count { it < y } * expansion
                    this += (x + xOffset) by (y + yOffset)
                }
            }
        }

        return buildList { for (l in galaxies.indices) for (r in l + 1..galaxies.lastIndex) this += l to r }.sumOf {
            val left = galaxies[it.first]
            val right = galaxies[it.second]
            abs(right.x - left.x) + abs(right.y - left.y)
        }
    }
}