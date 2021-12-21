package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay

class Day21(input: List<String>) : ADay(input) {

    private val playerRegex = Regex("Player (?<id>\\d+) starting position: (?<pos>\\d+)")

    override fun part1(): Int {

        val players = players()
        val dice = DeterministicDice()

        w@ while (true) for (p in players.indices)
            if (players[p].also { it.step((1..3).fold(0) { acc, _ -> acc + dice.roll() }) }.score >= 1000) break@w

        return players.minOf { it.score } * dice.rolls
    }

    override fun part2(): Any {

        val players = players()
        val steps = mutableListOf<Int>()

        for (a in 1..3) for (b in 1..3) for (c in 1..3) steps.add(a + b + c)

        var player1wins = 0L
        var player2wins = 0L

        return listOf(player1wins, player2wins).minOf { it }
    }

    private fun players() = input.map { playerRegex.matchEntire(it)!! }.map { Player(it.toInt("id"), it.toInt("pos")) }
}

internal data class Player(val id: Int, private var position: Int) {
    var score = 0
        private set

    fun step(step: Int) {
        position += step
        while (position > 10) position -= 10
        score += position
    }
}

internal class DeterministicDice {
    private var last = 0
    var rolls = 0
        private set

    fun roll() = (++last).also { rolls++; last %= 100 }
}
