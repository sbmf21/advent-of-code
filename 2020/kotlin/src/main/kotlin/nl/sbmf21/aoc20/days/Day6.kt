package nl.sbmf21.aoc20.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc20.Aoc

class Day6(aoc: Aoc) : ADay(aoc) {

    private val lines = input.joinToString(separator = "\n").split("\n\n")

    override fun number() = 6

    override fun part1() = lines.map { it.split("\n").joinToString(separator = "") }
        .map { it.toCharArray().distinct().size }
        .sum()

    override fun part2() = lines.map { it.split("\n") }
        .map { it.map { ln -> ln.toCharArray().distinct() }.reduce { acc, next -> acc.intersect(next).toList() }.size }
        .sum()
}
