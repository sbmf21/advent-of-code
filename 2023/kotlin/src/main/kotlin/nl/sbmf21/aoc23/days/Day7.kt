package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc23.days.Day7.Type.*

class Day7 : Day() {

    private val hands = input.map { it.split(" ", limit = 2) }

    override fun part1() = solve(listOf('A', 'K', 'Q', 'J', 'T', 'A', '9', '8', '7', '6', '5', '4', '3', '2'))

    override fun part2() = solve(listOf('A', 'K', 'Q', 'T', 'A', '9', '8', '7', '6', '5', '4', '3', '2', 'J')) { hand ->
        val possibleCharacters = hand
            .toCharArray()
            .distinct()
            .filterNot { it == 'J' }

        hand.withIndex()
            .filter { it.value == 'J' }
            .fold(listOf(hand)) { checks, (index, _) ->
                checks.flatMap { current ->
                    possibleCharacters.map { c ->
                        current.substring(0..<index) + c + current.substring(index + 1..current.lastIndex)
                    }
                }
            }
            .minOfOrNull(::getType) ?: FiveOfAKind
    }

    private fun solve(scores: List<Char>, getType: (String) -> Type = ::getType) = hands
        .map { (hand, bid) -> Card(hand, bid.toInt(), getType(hand), scores) }
        .sortedDescending()
        .withIndex()
        .sumOf { (index, card) -> (index + 1) * card.bid }

    private fun getType(hand: String) = hand.let {
        val charCount = it
            .map { c -> c to hand.count { cl -> cl == c } }
            .distinct()
            .sortedByDescending(Pair<Char, Int>::second)

        when (charCount.size) {
            1 -> FiveOfAKind
            2 -> if (charCount[0].second == 4) FourOfAKind else FullHouse
            3 -> if (charCount[0].second == 3) ThreeOfAKind else TwoPair
            4 -> OnePair
            else -> HighCard
        }
    }

    private class Card(val hand: String, val bid: Int, val type: Type, val scores: List<Char>) : Comparable<Card> {

        override fun compareTo(other: Card): Int {
            val compare = type.compareTo(other.type)
            return if (compare == 0) compareAt(other) else compare
        }

        private fun compareAt(other: Card, index: Int = 0): Int {
            val compare = charScore(index).compareTo(other.charScore(index))
            return if (compare == 0) compareAt(other, index + 1) else compare
        }

        private fun charScore(index: Int) = scores.indexOf(hand[index])
    }

    private enum class Type {
        FiveOfAKind,
        FourOfAKind,
        FullHouse,
        ThreeOfAKind,
        TwoPair,
        OnePair,
        HighCard;
    }
}