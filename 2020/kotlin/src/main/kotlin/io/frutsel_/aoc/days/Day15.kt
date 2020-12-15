package io.frutsel_.aoc.days

import io.frutsel_.aoc.Aoc
import io.frutsel_.aoc.common.ADay

class Day15(aoc: Aoc) : ADay(aoc) {

    private val numbers = input.joinToString(",").split(",").map { it.toInt() }

    override fun number() = 15

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
