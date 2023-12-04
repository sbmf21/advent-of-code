package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.mapToInts

class Day4 : Day() {

    private val cards = input.map { line ->
        val card = line.split(": ", limit = 2)
        val nums = card[1].split(" | ", limit = 2).map { it.split(" ").filterNot(String::isBlank).mapToInts() }
        Card(card[0].split(" ").last().toInt(), nums[0], nums[1])
    }

    override fun part1() = mapPoints { it * 2 }.values.sum()

    override fun part2(): Any {
        val points = mapPoints { it + 1 }
        val copies = cards.associateTo(mutableMapOf()) { it.id to 1 }

        cards.forEach {
            for (i in 1..(points[it.id] ?: 0)) {
                val current = it.id + i
                if (current !in copies) return@forEach
                copies[current] = copies[current]!! + copies[it.id]!!
            }
        }

        return copies.values.sum()
    }

    private fun mapPoints(handle: (Int) -> Int) = buildMap {
        cards.forEach { card ->
            card.winning.filter { it in card.numbers }.forEach { _ ->
                this[card.id] = if (card.id in this) handle(this[card.id]!!) else 1
            }
        }
    }

    private class Card(val id: Int, val winning: List<Int>, val numbers: List<Int>)
}