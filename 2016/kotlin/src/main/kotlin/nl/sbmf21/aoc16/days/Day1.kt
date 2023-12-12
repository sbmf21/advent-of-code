package nl.sbmf21.aoc16.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.math.Vector2i
import kotlin.math.abs

class Day1 : Day() {

    private val instructions = input.map { it.split(", ") }.flatten().map { it[0] to it.substring(1).toInt() }

    override fun part1() = plotPath { position, direction, steps -> position + direction * steps }

    override fun part2() = mutableSetOf<Vector2i>().let { seen ->
        plotPath { position, direction, steps ->
            var current = position

            repeat(steps) {
                current = current + direction
                if (current in seen) return distance(current)
                seen.add(current)
            }

            current
        }
    }

    private inline fun plotPath(handle: (Vector2i, Vector2i, Int) -> Vector2i): Int {
        var position = Vector2i(0, 0)
        var direction = 0

        instructions.forEach { (turn, steps) ->
            direction = turn(direction, turn)
            position = handle(position, DIRECTIONS[direction], steps)
        }

        return distance(position)
    }

    private fun distance(position: Vector2i) = abs(position.x) + abs(position.y)

    private companion object {
        val DIRECTIONS = listOf(
            Vector2i(0, 1),
            Vector2i(1, 0),
            Vector2i(0, -1),
            Vector2i(-1, 0),
        )

        fun turn(index: Int, direction: Char) = when (direction) {
            'L' -> (index - 1).mod(DIRECTIONS.size)
            'R' -> (index + 1).mod(DIRECTIONS.size)
            else -> throw Error("Unexpected turn: $direction")
        }
    }
}