package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.math.Vector2i
import nl.sbmf21.math.by

class Day14 : Day() {

    private val height = input.size
    private val width = input[0].length
    private val rocks = buildMap {
        input.forEachIndexed { y, row ->
            row.forEachIndexed { x, c ->
                when (c) {
                    '#' -> this[x by y] = false
                    'O' -> this[x by y] = true
                }
            }
        }
    }

    override fun part1(): Any {

        val positions = buildMap<Vector2i, Boolean> {
            putAll(rocks.filterValues { !it })

            rocks.filterValues { it }.forEach { (check, _) ->
                val newY = (filterKeys { it.x == check.x && it.y < check.y }.maxOfOrNull { (it, _) -> it.y } ?: -1) + 1
                this[check.x by newY] = true
            }
        }

        return positions
            .filterValues { it }
            .map { height - it.key.y }
            .sum()
    }

    /**
     * Yeah yeah, I know this takes 30 seconds to execute, but I don't care.
     * At least I have a solution for something after 3 days again.
     */
    override fun part2(): Any {

        var rocks = rocks

        val seen = mutableMapOf<Set<Vector2i>, Int>()
        var end = 0

        while (true) {
            if (rocks.keys in seen) break
            seen[rocks.keys] = end++
            rocks = Direction.entries.fold(rocks, ::move)
        }

        val start = seen[rocks.keys]!!
        end -= start
        val step = if (end == 0) 0 else (1_000_000_000 - start) % end

        val indexOfEndInSequence = start + step

        return seen
            .filterValues { it == indexOfEndInSequence }
            .firstNotNullOf { it.key }
            .filter { it !in this.rocks || this.rocks[it]!! }
            .sumOf { height - it.y }
    }

    private fun move(dish: Map<Vector2i, Boolean>, direction: Direction) = buildMap<Vector2i, Boolean> {
        putAll(dish.filterValues { !it })
        dish.filterValues { it }.forEach { (check, _) ->
            when (direction) {
                Direction.NORTH -> {
                    var newY = (filterKeys { it.x == check.x && it.y < check.y }
                        .maxOfOrNull { (it, _) -> it.y } ?: -1) + 1

                    // this is uggo but the lists aren't sorted
                    while (check.x by newY in this) newY++

                    this[check.x by newY] = true
                }

                Direction.WEST -> {
                    var newX = (filterKeys { it.y == check.y && it.x < check.x }
                        .maxOfOrNull { (it, _) -> it.x } ?: -1) + 1

                    // this is uggo but the lists aren't sorted
                    while (newX by check.y in this) newX++

                    this[newX by check.y] = true
                }

                Direction.SOUTH -> {
                    var newY = (filterKeys { it.x == check.x && it.y >= check.y }
                        .minOfOrNull { (it, _) -> it.y } ?: height) - 1

                    // this is uggo but the lists aren't sorted
                    while (check.x by newY in this) newY--

                    this[check.x by newY] = true
                }

                Direction.EAST -> {
                    var newX = (filterKeys { it.y == check.y && it.x >= check.x }
                        .minOfOrNull { (it, _) -> it.x } ?: width) - 1

                    // this is uggo but the lists aren't sorted
                    while (newX by check.y in this) newX--

                    this[newX by check.y] = true
                }
            }
        }
    }

    private enum class Direction {
        NORTH,
        WEST,
        SOUTH,
        EAST;

        operator fun inc() = entries[(ordinal + 1) % entries.size]
    }
}