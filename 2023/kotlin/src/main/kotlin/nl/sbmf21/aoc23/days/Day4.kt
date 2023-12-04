package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.mapToInts

class Day4 : Day() {

    private val cards = input.map {
        val card = it.split(": ", limit = 2)
        val nums = card[1].split(" | ", limit = 2)

        Card(
            card[0].split(" ").last().toInt(),
            nums[0].split(" ").filterNot(String::isBlank).mapToInts(),
            nums[1].split(" ").filterNot(String::isBlank).mapToInts(),
        )
    }

    override fun part1() = mapPoints { it * 2 }.values.sum()

    override fun part2(): Any {
        val points = mapPoints { it + 1 }
        val copies = cards.associate { it.id to 1 }.toMutableMap()

        cards.forEach {
            cards@ for (a in 1..copies[it.id]!!) {
                for (i in 1..(points[it.id] ?: 0)) {
                    val current = it.id + i
                    if (current !in copies) continue@cards
                    copies[current] = copies[current]!! + 1
                }
            }
        }

        return copies.values.sum()
    }

    private fun mapPoints(handle: (Int) -> Int) = buildMap {
        cards.forEach {
            it.winning.forEach { num ->
                if (num in it.numbers) {
                    this[it.id] = if (it.id in this) handle(this[it.id]!!) else 1
                }
            }
        }
    }

    private class Card(
        val id: Int,
        val winning: List<Int>,
        val numbers: List<Int>,
    )
}