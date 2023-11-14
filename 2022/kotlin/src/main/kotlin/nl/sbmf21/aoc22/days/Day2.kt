package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc22.days.Day2.Action.*
import nl.sbmf21.aoc22.days.Day2.RockPaperScissor.*

class Day2 : ADay() {

    override fun part1() = input.sumOf {
        val round = it.split(" ", limit = 2).run {
            Pair(RockPaperScissor.from(this[0]), RockPaperScissor.from(this[1]))
        }

        val action =
            if (round.second.beats(round.first)) WIN
            else if (round.first == round.second) DRAW
            else LOSE

        round.second.score + action.score
    }

    override fun part2() = input.sumOf {
        val round = it.split(" ", limit = 2).run {
            Pair(RockPaperScissor.from(this[0]), Action.from(this[1]))
        }

        val choice = when (round.second) {
            LOSE -> when (round.first) {
                ROCK -> SCISSOR
                PAPER -> ROCK
                SCISSOR -> PAPER
            }

            DRAW -> round.first
            WIN -> when (round.first) {
                ROCK -> PAPER
                PAPER -> SCISSOR
                SCISSOR -> ROCK
            }
        }

        round.second.score + choice.score
    }

    private enum class RockPaperScissor(val score: Int) {
        ROCK(1),
        PAPER(2),
        SCISSOR(3);

        fun beats(other: RockPaperScissor): Boolean {
            return other == when (this) {
                ROCK -> SCISSOR
                PAPER -> ROCK
                SCISSOR -> PAPER
            }
        }

        companion object {
            fun from(input: String) = when (input) {
                "A", "X" -> ROCK
                "B", "Y" -> PAPER
                "C", "Z" -> SCISSOR
                else -> throw Error()
            }
        }
    }

    private enum class Action(val score: Int) {
        LOSE(0),
        DRAW(3),
        WIN(6);

        companion object {
            fun from(input: String) = when (input) {
                "X" -> LOSE
                "Y" -> DRAW
                "Z" -> WIN
                else -> throw Error()
            }
        }
    }
}