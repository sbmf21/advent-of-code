package io.frutsel_.aoc.days

import io.frutsel_.aoc.Aoc
import io.frutsel_.aoc.common.ADay

class Day10(aoc: Aoc) : ADay(aoc) {

    private val adapters = input.map { it.toInt() }.sortedBy { it }
    private val max = adapters.last() + 3

    override fun number(): Int = 10

    override fun part1(): Int {

        var last = 0

        var diff1 = 0
        var diff3 = 1

        val numbers = adapters.toMutableList()

        numbers.forEach {

            when (it - last) {
                1 -> diff1++
                2 -> {
                }
                3 -> diff3++
                else -> throw Error()
            }

            last = it
        }

        return diff1 * diff3
    }

    override fun part2(): Int {
        TODO("WHAT IS THIS")
    }
}