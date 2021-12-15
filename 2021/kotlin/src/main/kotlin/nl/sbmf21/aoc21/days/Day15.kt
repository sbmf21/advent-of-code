package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc.common.Vector2
import nl.sbmf21.aoc.common.mapToInts

class Day15(input: List<String>) : ADay(input) {

    private val map = input.map { it.toCharArray().map { c -> "$c" }.mapToInts() }
    private val fatMap = buildFatMap()

    private val neighbors = listOf(
        Vector2(1, 0),
        Vector2(0, 1),
        Vector2(-1, 0),
        Vector2(0, -1),
    )

    override fun part1() = aStar(map)

    override fun part2() = aStar(fatMap)

    private fun buildFatMap(): List<List<Int>> {
        val fatMap = List(map.size * 5) { y -> MutableList(map[y % map.size].size * 5) { -1 } }

        for (yScale in 0 until 5) for (xScale in 0 until 5) for (y in map.indices) for (x in map[y].indices)
            fatMap[y + map.size * yScale][x + map[y].size * xScale] = (map[y][x] + (yScale + xScale))
                .run { if (this > 9) this - 9 else this }

        return fatMap
    }

    private fun aStar(map: List<List<Int>>): Int {
        val start = Vector2(0, 0)
        val end = Vector2(map[map.lastIndex].lastIndex, map.lastIndex)

        val costs = List(map.size) { y -> MutableList(map[y].size) { -1 } }.also { it[start.y][start.x] = 0 }
        val queue = mutableListOf(ChitonStep(start, 0))

        while (queue.isNotEmpty()) {
            val check = queue.minByOrNull { it.cost }!!
            if (check.pos == end) return check.cost

            queue.remove(check)
            getSteps(check, map).forEach { (p, c) ->
                costs[p.y][p.x].also {
                    if (it == -1 || it > c) {
                        costs[p.y][p.x] = c
                        queue.add(ChitonStep(p, c))
                    }
                }
            }
        }

        return -1
    }

    private fun getSteps(current: ChitonStep, map: List<List<Int>>) = neighbors
        .map { current.pos + it }
        .filter { it.y in map.indices && it.x in map[it.y].indices }
        .map { ChitonStep(it, current.cost + map[it.y][it.x]) }
}

internal data class ChitonStep(val pos: Vector2, val cost: Int)
