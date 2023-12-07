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

        when {
            charCount.size == 1 -> FiveOfAKind
            charCount.size == 2 && charCount[0].second == 4 -> FourOfAKind
            charCount.size == 2 && charCount[0].second == 3 -> FullHouse
            charCount.size == 3 && charCount[0].second == 3 -> ThreeOfAKind
            charCount.size == 3 && charCount[0].second == 2 && charCount[1].second == 2 -> TwoPair
            charCount.size == 4 && charCount[0].second == 2 -> OnePair
            charCount.size == 5 -> HighCard
            else -> throw Error("Done did do something bad: $hand")
        }
    }

    private class Card(val hand: String, val bid: Int, val type: Type, val scores: List<Char>) : Comparable<Card> {

        override fun compareTo(other: Card): Int {
            var compare = type.compareTo(other.type)
            if (compare == 0) compare = charScore(0).compareTo(other.charScore(0))
            if (compare == 0) compare = charScore(1).compareTo(other.charScore(1))
            if (compare == 0) compare = charScore(2).compareTo(other.charScore(2))
            if (compare == 0) compare = charScore(3).compareTo(other.charScore(3))
            if (compare == 0) compare = charScore(4).compareTo(other.charScore(4))
            if (compare == 0) throw Error("Comparing the same hands, this shouldn't occur :(")
            return compare
        }

        private fun charScore(at: Int) = scores.indexOf(hand[at])
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