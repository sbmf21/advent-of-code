package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.TODO
import nl.sbmf21.aoc.common.grid
import nl.sbmf21.aoc.common.util.Direction
import nl.sbmf21.aoc.common.util.Direction.Companion.plus
import nl.sbmf21.math.by

class Day21 : Day() {

    companion object {
        const val STEP_1_STEPS_DEFAULT = 64
        var STEP_1_STEPS = STEP_1_STEPS_DEFAULT
    }

    private val directions by lazy { Direction.straight() }
    private val grid = input.grid(::Tile)

    override fun part1(): Any {

        var current = listOf(grid.firstNotNullOf { entry -> if (entry.value.start) entry.value else null })

        repeat(STEP_1_STEPS) {
            current = current
                .flatMap { tile -> directions.map { tile + it } }
                .toSet()
                .mapNotNull { i ->
                    if (i in grid) {
                        val tile = grid[i]!!
                        if (tile.walkable) {
                            return@mapNotNull tile
                        }
                    }
                    null
                }
        }

        return current.size
    }

    override fun part2(): Any {
        return TODO
    }

    private class Tile(x: Int, y: Int, char: Char) {
        val pos = x by y
        val start = char == 'S'
        val walkable = char != '#'

        operator fun plus(direction: Direction) = pos + direction
    }
}