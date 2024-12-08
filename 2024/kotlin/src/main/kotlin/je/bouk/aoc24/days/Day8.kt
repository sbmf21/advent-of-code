package je.bouk.aoc24.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.grid
import nl.sbmf21.math.Vector2i

class Day8 : Day() {

    private val grid = input.grid { x, y, c -> c }
    private val antennas = grid.entries.filter { it.value != '.' }
    private val frequencies = antennas.map { it.value }.toSet()

    override fun part1() = solve { start, diff, nodes ->
        val p = start + diff
        if (p in grid) {
            nodes += p
        }
    }

    override fun part2() = solve { start, diff, nodes ->
        nodes += start
        var p = start + diff
        while (p in grid) {
            nodes += p
            p = p + diff
        }
    }

    private fun solve(find: (Vector2i, Vector2i, MutableSet<Vector2i>) -> Unit) = buildSet {
        frequencies.forEach { frequency ->
            val frequencyAntennas = antennas.filter { it.value == frequency }
            for (l in frequencyAntennas.indices) {
                val left = frequencyAntennas[l].key
                for (r in frequencyAntennas.indices) {
                    if (l == r) continue
                    val right = frequencyAntennas[r].key
                    find(right, right - left, this)
                }
            }
        }
    }.size
}