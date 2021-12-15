package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc.common.Vector2
import nl.sbmf21.aoc.common.mapToInts

class Day15(input: List<String>) : ADay(input) {

    private val map = input.map { it.toCharArray().map { c -> "$c" }.mapToInts() }
    private val neighbors = listOf(
        Vector2(1, 0),
        Vector2(0, 1),
        Vector2(-1, 0),
        Vector2(0, -1),
    )

    override fun part1() = run()

    override fun part2() = run(buildFatMap())

    private fun buildFatMap(): List<List<Int>> {
        val fatMap = List(map.size * 5) { y -> MutableList(map[y % map.size].size * 5) { -1 } }

        for (yScale in 0 until 5) for (xScale in 0 until 5) for (y in map.indices) for (x in map[y].indices)
            fatMap[y + map.size * yScale][x + map[y].size * xScale] = (map[y][x] + (yScale + xScale))
                .run { if (this > 9) this - 9 else this }

        return fatMap
    }

    private fun run(map: List<List<Int>> = this.map): Int {

        val start = Vector2(0, 0)
        val end = Vector2(map[map.lastIndex].lastIndex, map.lastIndex)

        val expenses = List(map.size) { y -> MutableList(map[y].size) { -1 } }.also { it[start.y][start.x] = 0 }
        val queue = mutableListOf(start)

        while (queue.isNotEmpty()) queue.removeLast().run {
            neighbors.map { this + it }.filter { it.y in expenses.indices && it.x in expenses[it.y].indices }.forEach {
                (expenses[y][x] + map[it.y][it.x]).run {
                    if (this < expenses[it.y][it.x] || expenses[it.y][it.x] == -1) {
                        expenses[it.y][it.x] = this
                        queue.add(it)
                    }
                }
            }
        }

        return expenses[end.y][end.x]
    }
}
