package nl.sbmf21.aoc20.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc.common.chineseRemainder
import nl.sbmf21.aoc20.Aoc

class Day13(aoc: Aoc, number: Int) : ADay(aoc, number) {

    private val start = input[0].toLong()
    private val regex = Regex("\\d+")
    private val scheme = input[1].split(',')
    private val busses = scheme.filter { regex.matches(it) }.map { it.toLong() }
    private val offsets = List(scheme.size) { i -> i }.filter { regex.matches(scheme[it]) }.map { (-it).toLong() }

    // @formatter:off ; This is way to long a one liner but I don't care.
    override fun part1() = busses.map { Pair(it - (start % it), it) }.minByOrNull { it.first }?.toList()?.fold(1, Long::times)!!
    // @formatter:on

    override fun part2() = chineseRemainder(busses, offsets)
}
