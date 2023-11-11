package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay

class Day21(input: List<String>) : ADay(input) {

    private val playerRegex = Regex("Player \\d+ starting position: (?<pos>\\d+)")
    private val players = input.map { playerRegex.matchEntire(it)!! }.map { Player(it.toInt("pos")) }
    private val deterministicDice = DeterministicDice()
    private val diracSteps = mapOf(3 to 1, 4 to 3, 5 to 6, 6 to 7, 7 to 6, 8 to 3, 9 to 1)

    override fun part1() = players.map { it.copy() }.also { players ->
        w@ while (true) for (p in players)
            if (p.also { it.step((1..3).fold(0) { acc, _ -> acc + deterministicDice.roll() }) }.score >= 1000) break@w
    }.minOf { it.score } * deterministicDice.rolls

    override fun part2() = players.run { play(this[0], this[1]).run { listOf(first, second) }.maxOf { it } }

    private fun play(first: Player, second: Player): Pair<Long, Long> {
        var win = 0L
        var loss = 0L

        if (second.score >= 21) return Pair(0, 1)

        diracSteps.forEach { (step, count) ->
            play(second, first.copy().also { it.step(step) }).also {
                win += it.second * count
                loss += it.first * count
            }
        }

        return Pair(win, loss)
    }

    private data class Player(private var position: Int) {
        var score = 0
            private set

        fun copy() = Player(position).also { it.score = score }
        fun step(step: Int) {
            position += step
            while (position > 10) position -= 10
            score += position
        }
    }

    private class DeterministicDice {
        private var last = 0
        var rolls = 0
            private set

        fun roll() = (++last).also { rolls++; last %= 100 }
    }
}