package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.FinalDay
import nl.sbmf21.aoc.common.util.Direction
import nl.sbmf21.aoc.common.util.Direction.Companion.noDir
import nl.sbmf21.aoc.common.util.Direction.EAST
import nl.sbmf21.aoc.common.util.Direction.SOUTH
import nl.sbmf21.math.Vector2i

class Day25 : FinalDay() {

    private val map = input.mapIndexed { y, row ->
        row.toCharArray().mapIndexed { x, pos ->
            when (pos) {
                '>' -> Cucumber(EAST, Vector2i(x, y))
                'v' -> Cucumber(SOUTH, Vector2i(x, y))
                else -> null
            }
        }
    }

    override fun solution(): Int {
        var step = 0
        var moved = true
        var map = this.map

        while (moved) {
            move(map, EAST).also { map = it.first; moved = it.second }
            move(map, SOUTH).also { map = it.first; moved = moved or it.second }
            step++
        }

        return step
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

    private data class Cucumber(val direction: Direction, val pos: Vector2i) {
        fun next(map: List<List<Cucumber?>>) = when (direction) {
            EAST -> {
                val cx = if (pos.x + 1 in map[pos.y].indices) pos.x + 1 else 0
                if (map[pos.y][cx] == null) Cucumber(direction, Vector2i(cx, pos.y)) else null
            }

            SOUTH -> {
                val cy = if (pos.y + 1 in map.indices) pos.y + 1 else 0
                if (map[cy][pos.x] == null) Cucumber(direction, Vector2i(pos.x, cy)) else null
            }

            else -> noDir(direction)
        }
    }
}