package io.frutsel_.aoc.days

import io.frutsel_.aoc.Aoc
import io.frutsel_.aoc.common.ADay

class Day5(aoc: Aoc) : ADay(aoc) {

    private val passports =
        input.map {
            it.replace(Regex("[FL]"), "0").replace(Regex("[BR]"), "1").toInt(2)
        }.sorted()

    override fun number(): Int = 5

    override fun part1(): Int = passports[passports.lastIndex]

    override fun part2(): Int = (passports[0]..passports[passports.lastIndex])
        .first { passports.contains(it - 1).and(!passports.contains(it)).and(passports.contains(it + 1)) }
}
