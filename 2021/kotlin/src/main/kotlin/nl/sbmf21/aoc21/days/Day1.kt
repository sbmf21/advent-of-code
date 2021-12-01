package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc21.Aoc

class Day1(aoc: Aoc, number: Int) : ADay(aoc, number) {

    private val numbers = input.map { it.toInt() }

    override fun part1() = countBigger(numbers)

    override fun part2() = countBigger(List(numbers.size - 2) {
        numbers.slice(it..it + 2).sum()
    })

    private fun countBigger(items: List<Int>) = items
        .filterIndexed { i, v -> i > 0 && items[i - 1] < v }
        .count()
}
