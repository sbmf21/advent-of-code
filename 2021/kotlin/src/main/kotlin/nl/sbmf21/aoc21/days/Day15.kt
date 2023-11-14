package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc.common.mapToInts
import nl.sbmf21.math.Vector2i

class Day15 : ADay() {

    private val map = input.map { it.toCharArray().map { c -> "$c" }.mapToInts() }
    private val fatMap = buildFatMap()

    private val neighbors = listOf(
        Vector2i(1, 0),
        Vector2i(0, 1),
        Vector2i(-1, 0),
        Vector2i(0, -1),
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
        val end = Vector2i(map[map.lastIndex].lastIndex, map.lastIndex)

        val costs = List(map.size) { y -> MutableList(map[y].size) { -1 } }
        val queue = mutableListOf(ChitonStep(Vector2i(0, 0), 0))

        while (queue.isNotEmpty()) {
            val check = queue.minByOrNull { it.cost }!!; queue.remove(check)
            if (check.pos == end) return check.cost

            getSteps(check, map).forEach { s ->
                costs[s.pos.y][s.pos.x]
                    .takeIf { it == -1 || it > s.cost }
                    ?.also { costs[s.pos.y][s.pos.x] = s.cost; queue.add(s) }
            }
        }

        return -1
    }

    private fun getSteps(current: ChitonStep, map: List<List<Int>>) = neighbors
        .map { current.pos + it }
        .filter { it.y in map.indices && it.x in map[it.y].indices }
        .map { ChitonStep(it, current.cost + map[it.y][it.x]) }

    private data class ChitonStep(val pos: Vector2i, val cost: Int)
}