package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay
import kotlin.math.abs
import nl.sbmf21.math.Vector2i as vec

class Day23(input: List<String>) : ADay(input) {

    private class Elf

    private val directions = listOf(
        /* N */ vec(0, -1) to listOf(vec(-1, -1), vec(0, -1), vec(1, -1)),
        /* S */ vec(0, 1) to listOf(vec(-1, 1), vec(0, 1), vec(1, 1)),
        /* W */ vec(-1, 0) to listOf(vec(-1, 1), vec(-1, 0), vec(-1, -1)),
        /* E */ vec(1, 0) to listOf(vec(1, 1), vec(1, 0), vec(1, -1)),
    )
    private val directionChecks = directions.flatMap { (_, checks) -> checks }.distinct()

    override fun part1() = run { grid, round, _ ->
        if (round == 10) {
            val x = abs(grid.minOf { it.key.x } - grid.maxOf { it.key.x }) + 1
            val y = abs(grid.minOf { it.key.y } - grid.maxOf { it.key.y }) + 1
            return x * y - grid.size
        }
    }

    override fun part2() = run { _, round, moved ->
        if (!moved) return round
    }

    private inline fun run(check: (Map<vec, Elf>, Int, Boolean) -> Unit): Int {
        val grid = getGrid()
        var directionIndex = 0
        var round = 0

        var moved = true

        while (true) {
            val proposals = mutableMapOf<Elf, vec>()

            grid.forEach { (pos, elf) ->
                for (i in 0..3) {
                    if (directionChecks.map { pos + it }.count { it !in grid } == directionChecks.size) break
                    val (direction, checks) = directions[(directionIndex + i) % 4]
                    if (checks.map { pos + it }.count { it !in grid } == checks.size) {
                        proposals[elf] = pos + direction
                        break
                    }
                }
            }

            proposals.filter { (_, pos) -> proposals.values.count { it == pos } == 1 }.onEach { (elf, pos) ->
                val keys = grid.filter { it.value == elf }.keys
                assert(keys.size == 1)
                keys.forEach(grid::remove)
                grid[pos] = elf
            }.count().let { if (it == 0) moved = false }

            directionIndex++
            round++

            check(grid, round, moved)
        }
    }

    private fun getGrid() = input.foldIndexed(mutableMapOf<vec, Elf>()) { y, map, line ->
        line.forEachIndexed { x, c -> if (c == '#') map[vec(x, y)] = Elf() }; map
    }
}