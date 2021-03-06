package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc.common.Vector2
import nl.sbmf21.aoc21.days.Direction.EAST
import nl.sbmf21.aoc21.days.Direction.SOUTH

class Day25(input: List<String>) : ADay(input) {

    private val map = input.mapIndexed { y, row ->
        row.toCharArray().mapIndexed { x, pos ->
            when (pos) {
                '>' -> Cucumber(EAST, Vector2(x, y))
                'v' -> Cucumber(SOUTH, Vector2(x, y))
                else -> null
            }
        }
    }

    override fun part1(): Int {
        var step = 0; var moved = true; var map = this.map

        while (moved) {
            move(map, EAST).also { map = it.first; moved = it.second }
            move(map, SOUTH).also { map = it.first; moved = moved or it.second }
            step++
        }

        return step
    }

    override fun part2(): Any {

        return -1
    }

    private fun move(map: List<List<Cucumber?>>, direction: Direction) =
        List(map.size) { y -> MutableList(map[y].size) { x -> map[y][x] } }.run {
            map.forEach {
                it.forEach { current ->
                    if (current != null && current.direction == direction) current.next(map)?.also { next ->
                        this[current.pos.y][current.pos.x] = null
                        this[next.pos.y][next.pos.x] = next
                    }
                }
            }
            Pair(this, this != map)
        }
}

internal enum class Direction { EAST, SOUTH }
internal data class Cucumber(val direction: Direction, val pos: Vector2) {
    fun next(map: List<List<Cucumber?>>) = when (direction) {
        EAST -> {
            val cx = if (pos.x + 1 in map[pos.y].indices) pos.x + 1 else 0
            if (map[pos.y][cx] == null) Cucumber(direction, Vector2(cx, pos.y)) else null
        }
        SOUTH -> {
            val cy = if (pos.y + 1 in map.indices) pos.y + 1 else 0
            if (map[cy][pos.x] == null) Cucumber(direction, Vector2(pos.x, cy)) else null
        }
    }
}
