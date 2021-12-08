package nl.sbmf21.aoc20.days

import nl.sbmf21.aoc.common.ADay
import kotlin.math.max
import kotlin.math.min

class Day11(input: List<String>) : ADay(input) {

    private val grid = input.map { line -> line.toCharArray().map { it } }

    override fun part1(): Int {
        var grid = this.grid
        var gridString = toString(grid)

        do {
            grid = grid.mapIndexed { lIndex, line ->
                line.mapIndexed { cIndex, it ->
                    when {
                        canSitAdjacent(grid, it, cIndex, lIndex) -> '#'
                        shouldBecomeEmptyAdjacent(grid, it, cIndex, lIndex) -> 'L'
                        else -> it
                    }
                }
            }
            val currentGridString = toString(grid)

            if (gridString == currentGridString) {
                break
            }

            gridString = currentGridString
        } while (true)

        return gridString.count { it == '#' }
    }

    override fun part2(): Int {
        var grid = this.grid
        var gridString = toString(grid)

        do {
            grid = grid.mapIndexed { lIndex, line ->
                line.mapIndexed { cIndex, it ->
                    when {
                        canSitInSight(grid, it, cIndex, lIndex) -> '#'
                        shouldBecomeEmptyInSight(grid, it, cIndex, lIndex) -> 'L'
                        else -> it
                    }
                }
            }
            val currentGridString = toString(grid)

            if (gridString == currentGridString) {
                break
            }

            gridString = currentGridString
        } while (true)

        return gridString.count { it == '#' }
    }

    private fun canSitAdjacent(grid: List<List<Char>>, current: Char, x: Int, y: Int) = current == 'L'
            && findAdjacent(grid, x, y) == 0

    private fun shouldBecomeEmptyAdjacent(grid: List<List<Char>>, current: Char, x: Int, y: Int) = current == '#'
            && findAdjacent(grid, x, y) >= 4

    private fun findAdjacent(grid: List<List<Char>>, x: Int, y: Int): Int {
        var adjacent = 0

        for (cy in max(y - 1, 0)..min(y + 1, grid.size - 1)) {
            for (cx in max(x - 1, 0)..min(x + 1, grid[y].size - 1)) {

                if (cx == x && cy == y) {
                    continue // do not count current seat
                }

                if (grid[cy][cx] == '#') {
                    adjacent++
                }
            }
        }

        return adjacent
    }

    private fun canSitInSight(grid: List<List<Char>>, current: Char, x: Int, y: Int) = current == 'L'
            && findInSight(grid, x, y) == 0

    private fun shouldBecomeEmptyInSight(grid: List<List<Char>>, current: Char, x: Int, y: Int) = current == '#'
            && findInSight(grid, x, y) >= 5

    private fun findInSight(grid: List<List<Char>>, x: Int, y: Int) = listOf(
        Pair(-1, -1),
        Pair(-1, 0),
        Pair(-1, 1),
        Pair(0, -1),
        Pair(0, 1),
        Pair(1, -1),
        Pair(1, 0),
        Pair(1, 1),
    ).map {
        var cx = x
        var cy = y

        do {
            cx += it.first
            cy += it.second

            if (cy < 0 || cy >= grid.size) break
            if (cx < 0 || cx >= grid[cy].size) break

            if (grid[cy][cx] == '#') {
                return@map true
            }
        } while (grid[cy][cx] == '.')

        false
    }.count { it }

    private fun toString(grid: List<List<Char>>) = grid
        .map { line -> line.joinToString("") { it.toString() } }
        .joinToString("") { it }
}
