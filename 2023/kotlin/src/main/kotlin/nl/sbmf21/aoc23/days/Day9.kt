package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.mapToLongs

class Day9 : Day() {

    private val oasisHistory = input.map { it.split(" ").mapToLongs() }.map { start ->
        var current = start
        val all = mutableListOf(current.first() to current.last())

        while (!current.all { it == 0L }) {
            val next = mutableListOf<Long>()
            for (idx in 0..<current.lastIndex) {
                next += current[idx + 1] - current[idx]
            }
            all += next.first() to next.last()
            current = next
        }

        all.reversed()
    }

    override fun part1() = solve { num, list -> num + list.second }
    override fun part2() = solve { num, list -> list.first - num }

    private fun solve(extrapolate: (Long, Pair<Long, Long>) -> Long) = oasisHistory.sumOf { history ->
        (1..history.lastIndex).fold(0L) { num, i -> extrapolate(num, history[i]) }
    }
}