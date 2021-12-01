package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc21.Aoc

class Day1(aoc: Aoc) : ADay(aoc) {

    private val numbers = input.map { it.toInt() }

    override fun number() = 1

    override fun part1() = countBigger(numbers)

    override fun part2() = countBigger(List(numbers.size - 2) {
        numbers[it] + numbers[it + 1] + numbers[it + 2]
    })

    private fun countBigger(items: List<Int>) = items
        .filterIndexed { index, item -> index > 0 && items[index - 1] < item }
        .count()
}
