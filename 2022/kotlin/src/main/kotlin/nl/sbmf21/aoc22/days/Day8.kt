package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.math.Vector2i

class Day8 : ADay() {

    private val grid = input.foldIndexed(mutableListOf<List<Int>>()) { y, grid, row ->
        grid.add(y, row.map { it.toString().toInt() })
        grid
    }.toList()

    private val directions = listOf(
        Vector2i(1, 0),
        Vector2i(-1, 0),
        Vector2i(0, 1),
        Vector2i(0, -1),
    )

    override fun part1() = mapTrees { position, tree ->
        directions.any { direction ->
            val check = position + direction
            while (check in grid) {
                if (grid[check.y][check.x] < tree) check += direction
                else return@any false
            }
            true
        }
    }.count { it }

    override fun part2() = mapTrees { position, tree ->
        directions.map { direction ->
            val check = position + direction
            var score = 0
            while (check in grid) {
                score++
                if (grid[check.y][check.x] < tree) check += direction
                else break
            }
            score
        }.run { subList(1, 4).fold(this[0]) { acc, score -> acc * score } }
    }.max()

    private fun <T : Any> mapTrees(map: (p: Vector2i, t: Int) -> T) = grid.flatMapIndexed { y: Int, row: List<Int> ->
        row.mapIndexed { x, tree -> map(Vector2i(x, y), tree) }
    }

    private operator fun List<List<Int>>.contains(point: Vector2i) =
        point.y in indices && point.x in this[point.y].indices
}