package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc23.days.Day16.Direction.*
import nl.sbmf21.aoc23.days.Day16.Direction.Companion.plus
import nl.sbmf21.math.Vector2i
import nl.sbmf21.math.by

class Day16 : Day() {

    override fun part1() = run(0 by 0 to RIGHT)

    override fun part2() = buildSet {
        input.first().forEachIndexed { x, _ -> this += x by 0 to DOWN }
        input.last().forEachIndexed { x, _ -> this += x by input.lastIndex to UP }

        input.forEachIndexed { y, line ->
            this += 0 by y to RIGHT
            this += line.lastIndex by y to LEFT
        }
    }.maxOf(::run)

    private fun run(start: Pair<Vector2i, Direction>): Int {
        val seen = mutableSetOf<Pair<Vector2i, Direction>>()
        val next = mutableSetOf(start)

        while (next.isNotEmpty()) {
            val current = next.first()
            val (pos, direction) = current
            next.remove(current)

            if (current in seen) continue
            if (pos.y < 0 || pos.y > input.lastIndex) continue
            if (pos.x < 0 || pos.x > input[pos.y].lastIndex) continue
            seen += current

            val char = input[pos.y][pos.x]

            when {
                char == '|' && (direction == LEFT || direction == RIGHT) -> {
                    next += pos + UP to UP
                    next += pos + DOWN to DOWN
                }

                char == '-' && (direction == UP || direction == DOWN) -> {
                    next += pos + LEFT to LEFT
                    next += pos + RIGHT to RIGHT
                }

                char == '/' -> next += when (direction) {
                    UP -> pos + RIGHT to RIGHT
                    DOWN -> pos + LEFT to LEFT
                    LEFT -> pos + DOWN to DOWN
                    RIGHT -> pos + UP to UP
                }

                char == '\\' -> next += when (direction) {
                    UP -> pos + LEFT to LEFT
                    DOWN -> pos + RIGHT to RIGHT
                    RIGHT -> pos + DOWN to DOWN
                    LEFT -> pos + UP to UP
                }

                else -> next += pos + direction to direction
            }
        }

        return seen.map { it.first }.toSet().size
    }

    private enum class Direction(private val vector: Vector2i) {
        RIGHT(1 by 0),
        DOWN(0 by 1),
        LEFT(-1 by 0),
        UP(0 by -1);

        operator fun inc() = at(ordinal + 1)
        operator fun dec() = at(ordinal - 1)

        companion object {
            operator fun Vector2i.plus(direction: Direction) = plus(direction.vector)
            private fun at(index: Int) = entries[index.mod(entries.size)]
        }
    }
}