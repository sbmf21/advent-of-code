package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.TODO
import nl.sbmf21.aoc.common.grid
import nl.sbmf21.math.Vector2i
import nl.sbmf21.math.by

class Day21 : Day() {

    companion object {
       const val STEP_1_STEPS_DEFAULT = 64
       var STEP_1_STEPS = STEP_1_STEPS_DEFAULT
    }

    private val grid = input.grid(::Tile)

    override fun part1(): Any {

        var current = listOf(grid.firstNotNullOf { entry -> if (entry.value.start) entry.value else null })

        for (step in 1..STEP_1_STEPS) {
            current = current
                .flatMap { tile -> Direction.entries.map { tile + it } }
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

    private class Tile(val x: Int, val y: Int, char: Char) {
        val pos = x by y
        val start = char == 'S'
        val walkable = char != '#'

        operator fun plus(direction: Direction): Vector2i {
            return pos + direction.vec
        }
    }

    private enum class Direction(val x: Int, val y: Int) {
        UP(0, -1),
        DOWN(0, 1),
        LEFT(-1, 0),
        RIGHT(1, 0);

        val vec = x by y
    }
}