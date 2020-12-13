package io.frutsel_.aoc.days

import io.frutsel_.aoc.Aoc
import io.frutsel_.aoc.common.ADay
import io.frutsel_.aoc.common.chineseRemainder

class Day13(aoc: Aoc) : ADay(aoc) {

    private val earliest = input[0].toLong()
    private val regex = Regex("\\d+")
    private val scheme = input[1].split(',')
    private val busses = scheme.filter { regex.matches(it) }.map { it.toLong() }
    private val offsets = scheme.mapIndexed { i, _ -> i }.filter { regex.matches(scheme[it]) }.map { (-it).toLong() }

    override fun number() = 13

    override fun part1() = busses.map { Pair(it - (earliest % it), it) }.minByOrNull { it.first }.prod()

    override fun part2() = chineseRemainder(busses, offsets)
}

private fun Pair<Long, Long>?.prod() = this!!.first * this.second
