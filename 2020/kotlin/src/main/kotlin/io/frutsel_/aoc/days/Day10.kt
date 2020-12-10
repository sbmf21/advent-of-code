package io.frutsel_.aoc.days

import io.frutsel_.aoc.Aoc
import io.frutsel_.aoc.common.ADay
import kotlin.math.max

class Day10(aoc: Aoc) : ADay(aoc) {

    private val adapters = input.map { it.toInt() }.sortedBy { it }

    override fun number(): Int = 10

    override fun part1(): Int {

        var last = 0
        var diff1 = 0
        var diff3 = 1

        adapters.toMutableList().forEach {
            when (it - last) {
                1 -> diff1++
                3 -> diff3++
            }

            last = it
        }

        return diff1 * diff3
    }

    override fun part2(): Long {

        val numbers = adapters.asReversed().toMutableList()
        numbers.add(0)

        val pathsList = mutableMapOf<Int, Long>(Pair(numbers[0], 1))

        for (i in 1 until numbers.size) {
            pathsList[numbers[i]] = pathsList
                .filter { (it.key - numbers[i]) <= 3 }
                .map { it.value }
                .sum()
        }

        return pathsList.map { it.value }.last()
    }
}