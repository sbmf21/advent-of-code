package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.mapToInts

class Day1 : Day() {

    private val numbers = input.mapToInts()

    override fun part1() = countBigger(numbers)
    override fun part2() = countBigger(List(numbers.size - 2) { numbers.slice(it..it + 2).sum() })
    private fun countBigger(items: List<Int>) = items.filterIndexed { i, v -> i > 0 && items[i - 1] < v }.count()
}