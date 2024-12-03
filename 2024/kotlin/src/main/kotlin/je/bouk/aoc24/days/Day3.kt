package je.bouk.aoc24.days

import nl.sbmf21.aoc.common.Day

class Day3 : Day() {

    private companion object {
        val DEFAULT_PATTERN = Regex("""mul\((?<x>\d+),(?<y>\d+)\)""")
        val FEATURE_PATTERN = Regex("""mul\((?<x>\d+),(?<y>\d+)\)|do(?:n't)?\(\)""")
    }

    private val instructions = input.joinToString("")

    override fun part1() = solve(DEFAULT_PATTERN)
    override fun part2() = solve(FEATURE_PATTERN)

    private fun solve(pattern: Regex) = with(State()) {
        pattern.findAll(instructions).sumOf { match ->
            when {
                match.value == "do()" -> enabled = true
                match.value == "don't()" -> enabled = false
                enabled -> return@sumOf match.num('x') * match.num('y')
            }; 0
        }
    }

    private fun MatchResult.num(name: Char) = groups["$name"]!!.value.toInt()

    private class State(var enabled: Boolean = true)
}