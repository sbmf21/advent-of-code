package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.mapToLongs

class Day9 : Day() {

    private val oasisHistory = input.map { it.split(" ").mapToLongs() }.map { start ->
        buildList history@{
            var current = start
            while (!current.all { it == 0L }) current = buildList {
                this@history += current.first() to current.last()
                for (idx in 0..<current.lastIndex) this += current[idx + 1] - current[idx]
            }
            reverse()
        }
    }

    override fun part1() = solve { num, list -> num + list.second }
    override fun part2() = solve { num, list -> list.first - num }

    private fun solve(extrapolate: (Long, Pair<Long, Long>) -> Long) = oasisHistory.sumOf { it.fold(0L, extrapolate) }
}