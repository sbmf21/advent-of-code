package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay

class Day21(input: List<String>) : ADay(input) {

    private val playerRegex = Regex("Player (?<id>\\d+) starting position: (?<pos>\\d+)")
    private val players = input.map { playerRegex.matchEntire(it)!! }.map { Player(it.toInt("id"), it.toInt("pos")) }
    private val dice = DeterministicDice()

    override fun part1() = players.map { it.copy() }.also { players ->
        w@ while (true) for (p in players)
            if (p.also { it.step((1..3).fold(0) { acc, _ -> acc + dice.roll() }) }.score >= 1000) break@w
    }.minOf { it.score } * dice.rolls

    override fun part2() = players.run { play(this[0], this[1], steps()).run { listOf(first, second) }.maxOf { it } }

    private fun play(first: Player, second: Player, steps: Map<Int, Int>): Pair<Long, Long> {
        var win = 0L
        var loss = 0L

        if (second.score >= 21) return Pair(0, 1)

        steps.forEach { (step, count) ->
            play(second.copy(), first.copy().also { it.step(step) }, steps).also {
                win += it.second * count
                loss += it.first * count
            }
        }

        return Pair(win, loss)
    }

    private fun steps(): Map<Int, Int> {
        val steps = mutableListOf<Int>()
        for (a in 1..3) for (b in 1..3) for (c in 1..3) steps.add(a + b + c)
        return steps.toSet().fold(mutableMapOf()) { m, i -> m[i] = steps.count { n -> n == i }; m }
    }
}

internal data class Player(val id: Int, private var position: Int) {
    var score = 0
        private set

    fun copy() = Player(id, position).also { it.score = score }

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
