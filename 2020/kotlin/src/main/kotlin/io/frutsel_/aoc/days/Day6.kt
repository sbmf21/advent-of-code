package io.frutsel_.aoc.days

import io.frutsel_.aoc.ADay
import io.frutsel_.aoc.Aoc

class Day6(aoc: Aoc) : ADay(aoc) {

    private val lines = input.joinToString(separator = "\n").split("\n\n")

    override fun number(): Int = 6

    override fun part1(): Number = lines.map { it.split("\n").joinToString(separator = "") }
        .map { it.toCharArray().distinct().size }
        .sum()

    override fun part2(): Number = lines.map { it.split("\n") }
        .map { it.map { ln -> ln.toCharArray().distinct() }.reduce { acc, next -> acc.intersect(next).toList() }.size }
        .sum()
}
