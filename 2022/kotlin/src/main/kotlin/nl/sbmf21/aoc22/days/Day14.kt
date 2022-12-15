package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc22.days.CaveMaterial.*
import nl.sbmf21.math.Vector2i
import nl.sbmf21.math.towards

private enum class CaveMaterial { AIR, STONE, SAND; }
private typealias Cave = MutableMap<Vector2i, CaveMaterial>

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

    override fun part1() = run { cave, point, count ->
        if (point !in cave) return count
        cave[point] == AIR
    }

    override fun part2() = run(true) { cave, point, _ -> cave[point] == AIR }

    private inline fun run(floor: Boolean = false, predicate: (Cave, Vector2i, Int) -> Boolean): Int {
        val cave = buildCave(floor)
        var count = 0
        while (true) {
            var lastStep = start
            while (true) lastStep = steps
                .map { lastStep + it }
                .firstOrNull { v -> predicate(cave, v, count) }
                ?: break

            cave[lastStep] = SAND
            count++

            if (lastStep == start) return count
        }
    }

    private fun buildCave(floor: Boolean): Cave {

        val cave: Cave = mutableMapOf()
        val maxY = paths.flatten().maxOf { it.y }.run { if (floor) this + 2 else this }
        val maxX = if (floor) start.x + maxY else paths.flatten().maxOf { it.x }
        val minX = if (floor) start.x - maxY else paths.flatten().minOf { it.x }

        for (y in 0..maxY) for (x in minX..maxX) cave[Vector2i(x, y)] = AIR
        if (floor) for (x in minX..maxX) cave[Vector2i(x, maxY)] = STONE

        paths.forEach { steps ->
            var left = steps[0]
            for (i in 1 until steps.size) {
                val right = steps[i]
                for (y in left.y towards right.y) for (x in left.x towards right.x) cave[Vector2i(x, y)] = STONE
                left = right
            }
        }

        return cave
    }
}
