package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.prod

class Day6 : Day() {

    private val races: Map<Int, Long>
    private val totalTime: Int
    private val totalDistance: Long

    init {
        val times = input[0].nums()
        val distances = input[1].nums()

        races = times.withIndex().associate { it.value.toInt() to distances[it.index].toLong() }
        totalTime = times.joinToString("").toInt()
        totalDistance = distances.joinToString("").toLong()
    }

    override fun part1() = races
        .map { (maxTime, maxDistance) -> race(maxTime, maxDistance) }
        .prod()

    override fun part2() = race(totalTime, totalDistance)

    private fun race(maxTime: Int, maxDistance: Long) = (1..<maxTime).count { ms ->
        ms * (maxTime - ms).toLong() > maxDistance
    }

    private companion object {
        private const val DELIMITER = ":"
        private val WHITESPACE = Regex("""\s+""")

        fun String.nums() = substringAfter(DELIMITER).split(WHITESPACE).filterNot(String::isBlank)
    }
}