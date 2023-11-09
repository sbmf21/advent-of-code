package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay

class Day10(input: List<String>) : ADay(input) {

    private val opening = listOf('(', '[', '{', '<')
    private val closing = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')
    private val points = mapOf(')' to Pair(3, 1), ']' to Pair(57, 2), '}' to Pair(1197, 3), '>' to Pair(25137, 4))

    override fun part1() = run({ it }, { null })
        .fold(mutableMapOf(')' to 0, ']' to 0, '}' to 0, '>' to 0)) { acc, c -> acc[c] = acc[c]!! + 1; acc }
        .map { it.value * points[it.key]!!.first }
        .sum()

    override fun part2() = run({ null }, { it })
        .map { it.reversed().fold(0L) { acc, c -> acc * 5 + points[closing[c]]!!.second } }
        .run { sorted()[size / 2] }

    private fun <C> run(c: (c: Char) -> C?, b: (b: List<Char>) -> C?): List<C> = input.map {
        val blocks = mutableListOf<Char>()

        it.toCharArray().forEach { c ->
            if (opening.contains(c)) blocks.add(c)
            else if (closing[blocks.last()] == c) blocks.removeLast()
            else return@map c(c)
        }

        return@map b(blocks.toList())
    }.filterNotNull()
}