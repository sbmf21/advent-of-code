package io.frutsel_.aoc.days

import io.frutsel_.aoc.Aoc
import io.frutsel_.aoc.Day

class Day5(aoc: Aoc) : Day(aoc) {
    private val passports =
        input.map {
            it.replace("[FL]".toRegex(), "0").replace("[BR]".toRegex(), "1").toInt(2)
        }.sorted()

    override fun number(): Int = 5

    override fun part1(): Number = passports[passports.lastIndex]

    override fun part2(): Number = (passports[0]..passports[passports.lastIndex])
        .first { passports.contains(it - 1).and(!passports.contains(it)).and(passports.contains(it + 1)) }
}
