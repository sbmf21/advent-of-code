package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.Day

class Day20 : Day() {

    private val numbers = input.mapIndexed { index, value -> MixNumber(index, value.toLong()) }

    override fun part1() = numbers
        .mapTo(mutableListOf()) { it }
        .also(this::mix)
        .run(this::groveCoordinate)

    override fun part2() = numbers
        .mapTo(mutableListOf()) { it * 811_589_153 }
        .also { repeat(10) { _ -> mix(it) } }
        .run(this::groveCoordinate)

    private fun mix(numbers: MutableList<MixNumber>) = numbers.indices.forEach { originalIndex ->
        val number = numbers.first { it.index == originalIndex }
        val currentIndex = numbers.indexOf(number)
        numbers.removeAt(currentIndex)
        numbers.add((currentIndex + number.value).mod(numbers.size), number)
    }

    private fun groveCoordinate(numbers: List<MixNumber>) = numbers.indexOfFirst { it.value == 0L }.let { start ->
        listOf(1000, 2000, 3000).sumOf { numbers[(start + it) % numbers.size].value }
    }

    private data class MixNumber(val index: Int, val value: Long) {
        operator fun times(other: Long) = MixNumber(index, value * other)
    }
}