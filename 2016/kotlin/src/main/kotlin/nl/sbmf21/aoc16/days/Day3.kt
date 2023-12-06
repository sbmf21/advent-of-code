package nl.sbmf21.aoc16.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.mapToInts

class Day3 : Day() {

    private val numbers = input.map { it.split(WHITESPACE).filterNot(String::isBlank).mapToInts() }

    override fun part1() = numbers.count(::isValid)

    override fun part2() = numbers.chunked(3)
        .flatMap { buildList { for (i in 0..<3) add(listOf(it[0][i], it[1][i], it[2][i])) } }
        .count(::isValid)

    private fun isValid(triangle: List<Int>): Boolean {
        return triangle[0] + triangle[1] > triangle[2]
            && triangle[1] + triangle[2] > triangle[0]
            && triangle[2] + triangle[0] > triangle[1]
    }

    private companion object {
        val WHITESPACE = Regex("""\s+""")
    }
}