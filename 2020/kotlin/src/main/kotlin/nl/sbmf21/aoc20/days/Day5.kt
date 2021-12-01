package nl.sbmf21.aoc20.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc20.Aoc

class Day5(aoc: Aoc, number: Int) : ADay(aoc, number) {

    private val passports = input.map {
        it.replace(Regex("[FL]"), "0").replace(Regex("[BR]"), "1").toInt(2)
    }.sorted()

    override fun part1() = passports[passports.lastIndex]

    override fun part2() = (passports[0]..passports[passports.lastIndex])
        .first { passports.contains(it - 1).and(!passports.contains(it)).and(passports.contains(it + 1)) }
}
