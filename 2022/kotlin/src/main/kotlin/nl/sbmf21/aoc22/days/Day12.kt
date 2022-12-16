package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.math.Vector2i

private val steps = listOf(
    Vector2i(-1, 0),
    Vector2i(1, 0),
    Vector2i(0, -1),
    Vector2i(0, 1),
)

class Day12(input: List<String>) : ADay(input) {

    private lateinit var start: Vector2i
    private lateinit var target: Vector2i
    private val grid = input.mapIndexed { y, line ->
        line.mapIndexed { x, c ->
            when (c) {
                'S' -> {
                    start = Vector2i(x, y)
                    0
                }

                'E' -> {
                    target = Vector2i(x, y)
                    'z' - 'a'
                }

                else -> c - 'a'
            }
        }
    }

    override fun part1() = aStar(listOf(start))

    override fun part2() = aStar(grid
        .mapIndexed { y, row -> row.mapIndexed { x, c -> Vector2i(x, y) to c } }
        .flatten()
        .mapNotNullTo(mutableListOf()) { if (it.second == 0) it.first else null })

    private fun aStar(start: List<Vector2i>): Int {

        val open = ArrayDeque(start.map { 0 to it })
        val closed = mutableSetOf<Vector2i>()

        while (open.isNotEmpty()) {
            val current = open.removeFirst()
            if (current.second == target) return current.first
            if (current.second in closed) continue
            closed.add(current.second)
            open.addAll(getNeighbours(current.second).map { current.first + 1 to it })
        }

        throw Error("No path.")
    }

    private fun getNeighbours(pos: Vector2i) = steps
        .map { pos + it }
        .filter { it.y in grid.indices && it.x in grid[it.y].indices }
        .filter { grid[pos.y][pos.x] + 1 >= grid[it.y][it.x] }
}
