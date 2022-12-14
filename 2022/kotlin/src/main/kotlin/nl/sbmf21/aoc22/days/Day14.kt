package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc22.days.CaveMaterial.*
import nl.sbmf21.math.Vector2i
import kotlin.math.min

private enum class CaveMaterial { AIR, STONE, SAND; }
private typealias Cave = MutableMap<Int, MutableMap<Int, CaveMaterial>>

class Day14(input: List<String>) : ADay(input) {

    private val paths = input.map { line ->
        line
            .split(" -> ")
            .map { it.split(",", limit = 2) }
            .map { Vector2i(it[0].toInt(), it[1].toInt()) }
    }
    private val start = Vector2i(500, 0)
    private val steps = listOf(
        Vector2i(0, 1),
        Vector2i(-1, 1),
        Vector2i(1, 1),
    )

    override fun part1() = run { grid, point, count ->
        if (point.y !in grid || point.x !in grid[point.y]!!) return count
        else grid[point.y]!![point.x] == AIR
    }

    override fun part2() = run(true) { grid, point, _ ->
        point.y in grid && point.x in grid[point.y]!! && grid[point.y]!![point.x] == AIR
    }

    private inline fun run(floor: Boolean = false, predicate: (Cave, Vector2i, Int) -> Boolean): Int {
        val cave = buildCave(floor)
        var count = 0
        while (true) {
            var lastStep = start
            while (true) lastStep = steps
                .map { lastStep + it }
                .firstOrNull { v -> predicate(cave, v, count) }
                ?: break

            cave[lastStep.y]!![lastStep.x] = SAND
            count++

            if (lastStep == start) return count
        }
    }

    private fun buildCave(floor: Boolean): Cave {

        val cave: Cave = mutableMapOf()
        val maxY = paths.flatten().maxOf { it.y }.run { if (floor) this + 2 else this }
        val minY = min(0, paths.flatten().minOf { it.y })
        val maxX = if (floor) start.x + maxY else paths.flatten().maxOf { it.x }
        val minX = if (floor) start.x - maxY else paths.flatten().minOf { it.x }

        for (y in minY..maxY) {
            cave[y] = mutableMapOf()
            for (x in minX..maxX) cave[y]!![x] = AIR
        }
        if (floor) for (x in minX..maxX) cave[maxY]!![x] = STONE

        paths.forEach { steps ->
            var first = steps[0]
            for (i in 1 until steps.size) {
                val second = steps[i]
                val yRange = if (first.y > second.y) first.y downTo second.y else first.y..second.y
                val xRange = if (first.x > second.x) first.x downTo second.x else first.x..second.x
                for (cy in yRange) for (cx in xRange) cave[cy]!![cx] = STONE
                first = second
            }
        }

        return cave
    }
}
