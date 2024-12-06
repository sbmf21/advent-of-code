package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.util.Direction
import nl.sbmf21.aoc.common.util.Direction.Companion.plus
import nl.sbmf21.math.Vector2i
import nl.sbmf21.math.by
import java.util.*

class Day17 : Day() {

    private val maxX = input[0].lastIndex
    private val maxY = input.lastIndex

    override fun part1() = solve(1, 3)

    override fun part2() = solve(4, 10)

    private fun solve(minStraight: Int, maxStraight: Int): Int {
        val cache = mutableMapOf<State, Int>()
        var min = Int.MAX_VALUE

        val tests = PriorityQueue<State>(Comparator.comparing { it.heatLoss })
        tests.add(State(0 by 0, Direction.RIGHT, 0, 0))
        tests.add(State(0 by 0, Direction.DOWN, 0, 0))

        while (!tests.isEmpty()) {
            val current = tests.remove()
            if (current.heatLoss > min) break

            current.step(input, minStraight, maxStraight).forEach { step ->
                if (step.position.x == maxX && step.position.y == maxY) {
                    if (step.heatLoss < min) min = step.heatLoss
                } else if (step.heatLoss < min) step.copy(heatLoss = 0).let {
                    if (it !in cache || step.heatLoss < cache[it]!!) {
                        cache[it] = step.heatLoss
                        tests.add(step)
                    }
                }
            }
        }

        return min
    }

    private data class State(
        val position: Vector2i,
        val direction: Direction,
        val straights: Int,
        val heatLoss: Int
    ) {
        fun step(input: List<String>, minStraight: Int, maxStraight: Int) = buildList {
            val position = position + direction

            if (straights >= maxStraight || position !in input) return@buildList

            val heatLoss = heatLoss + "${input[position]}".toInt()

            if (straights + 1 >= minStraight) {
                this += State(position, directions.previous(direction), 0, heatLoss)
                this += State(position, directions.next(direction), 0, heatLoss)
            }

            this += State(position, direction, straights.inc(), heatLoss)
        }
    }

    private companion object {
        val directions = Direction.straight()

        operator fun List<String>.contains(vector: Vector2i) =
            vector.y in indices && vector.x in this[vector.y].indices

        operator fun List<String>.get(vector: Vector2i) =
            this[vector.y][vector.x]
    }
}