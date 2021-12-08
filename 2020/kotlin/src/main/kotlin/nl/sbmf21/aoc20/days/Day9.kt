package nl.sbmf21.aoc20.days

import nl.sbmf21.aoc.common.ADay

class Day9(input: List<String>) : ADay(input) {

    private val numbers = input.map { it.toLong() }

    override fun part1(): Long {
        for (i in 25 until numbers.size) {
            if (!isSum(i)) {
                return numbers[i]
            }
        }

        return -1
    }

    override fun part2(): Long {
        val weak = part1()

        for (i in numbers.indices) {
            var acc = numbers[i]

            for (j in i + 1 until numbers.size) {
                acc += numbers[j]

                if (acc > weak) {
                    break
                }

                if (acc == weak) {
                    val subList = numbers.subList(i, j)
                    return subList.minOrNull()?.plus(subList.maxOrNull()!!)!!
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
                    return true
                }
            }
        }

        return false
    }
}
