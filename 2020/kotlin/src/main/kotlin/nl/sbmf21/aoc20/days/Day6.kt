package nl.sbmf21.aoc20.days

import nl.sbmf21.aoc.common.ADay

class Day6(input: List<String>) : ADay(input) {

    private val lines = input.joinToString(separator = "\n").split("\n\n")

    override fun part1() = lines.map { it.split("\n").joinToString(separator = "") }
        .map { it.toCharArray().distinct().size }
        .sum()

    override fun part2() = lines.map { it.split("\n") }
        .map { it.map { ln -> ln.toCharArray().distinct() }.reduce { acc, next -> acc.intersect(next).toList() }.size }
        .sum()
}
