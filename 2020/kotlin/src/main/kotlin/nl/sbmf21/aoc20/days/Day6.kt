package nl.sbmf21.aoc20.days

import nl.sbmf21.aoc.common.ADay

class Day6 : ADay() {

    private val lines = input.joinToString(separator = "\n").split("\n\n")

    override fun part1() = lines
        .map { it.split("\n").joinToString(separator = "") }
        .sumOf { it.toCharArray().distinct().size }

    override fun part2() = lines
        .map { it.split("\n") }
        .sumOf { it.map { ln -> ln.toCharArray().distinct().toSet() }.reduce { acc, next -> acc.intersect(next) }.size }
}