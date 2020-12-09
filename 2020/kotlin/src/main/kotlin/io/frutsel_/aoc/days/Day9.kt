package io.frutsel_.aoc.days

import io.frutsel_.aoc.Aoc
import io.frutsel_.aoc.common.ADay

class Day9(aoc: Aoc) : ADay(aoc) {

    private val numbers = input.map { it.toLong() }
    private var weak = -1

    override fun number(): Int = 9

    override fun part1(): Int {
        for (i in 25 until numbers.size) {
            if (!isSum(i)) {
                weak = numbers[i].toInt()
                return weak
            }
        }
        return -1
    }

    override fun part2(): Int {
        for (i in numbers.indices) {
            var acc = numbers[i]

            for (j in i + 1 until numbers.size) {
                acc += numbers[j]

                if (acc > weak) {
                    break
                }

                if (acc == weak.toLong()) {
                    val subList = numbers.subList(i, j).map { it.toInt() }
                    return subList.minOrNull()?.plus(subList.minOrNull()!!)!!
                }
            }
        }

        return -1
    }

    private fun isSum(index: Int): Boolean {
        for (i in index - 25..index) {
            for (j in index - 25..index) {
                if (i == j) {
                    continue
                }

                if (numbers[i] + numbers[j] == numbers[index]) {
                    return true;
                }
            }
        }

        return false;
    }
}