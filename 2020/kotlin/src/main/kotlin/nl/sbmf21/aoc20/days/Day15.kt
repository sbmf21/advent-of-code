package nl.sbmf21.aoc20.days

import nl.sbmf21.aoc.common.ADay

class Day15 : ADay() {

    private val numbers = input.joinToString(",").split(",").map { it.toInt() }

    override fun part1() = run(2020)

    override fun part2() = run(30_000_000)

    private fun run(max: Int): Int {
        val turns = mutableMapOf<Int, Pair<Int, Int>>()
        numbers.forEachIndexed { turn, number -> turns[number] = newTurnPair(turn) }
        var last = numbers.last()

        for (turn in turns.size until max) {
            last = evalLast(turns[last]!!)
            turns[last] = turn(turns[last], turn)
        }

        return last
    }

    private fun evalLast(last: Pair<Int, Int>) = if (last.second == -1) 0 else last.first - last.second
    private fun newTurnPair(turn: Int) = Pair(turn, -1)
    private fun turn(last: Pair<Int, Int>?, turn: Int) = if (last == null) newTurnPair(turn) else Pair(turn, last.first)
}