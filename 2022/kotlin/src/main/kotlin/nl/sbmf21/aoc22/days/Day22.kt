package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.math.Vector2i

class Day22(input: List<String>) : ADay(input) {

    private val regex = Regex("(\\d+)([A-Z])?")
    private val actions = regex.findAll(input.last()).map { it.groupValues }.fold(mutableListOf<Any>()) { map, match ->
        map.add(match[1].toInt())
        match[2].let { if (it.isNotEmpty()) map.add(it) }
        map
    }
    private val data = input.subList(0, input.size - 2).foldIndexed(mutableMapOf<Vector2i, Boolean>()) { y, map, line ->
        line.mapIndexed { x, c -> x to c }.filter { (_, c) -> c != ' ' }
            .forEach { (x, c) -> map[Vector2i(x + 1, y + 1)] = c == '.' }
        map
    }
    private val first = data.keys.filter { it.y == data.keys.minOf { p -> p.y } }.minByOrNull { it.x }!!

    override fun part1(): Any {

        val directions = listOf(
            Vector2i(1, 0),
            Vector2i(0, 1),
            Vector2i(-1, 0),
            Vector2i(0, -1),
        )

        var pos = first
        var rotation = 0

        actions.forEach { action ->
            when (action) {
                "R" -> rotation = (rotation + 1).mod(4)
                "L" -> rotation = (rotation - 1).mod(4)
                else -> (0 until action as Int).forEach { _ ->
                    val next = (pos + directions[rotation]).run {
                        if (this in data) this else when (rotation) {
                            0 -> data.keys.filter { it.y == pos.y }.minByOrNull { it.x }!!
                            1 -> data.keys.filter { it.x == pos.x }.minByOrNull { it.y }!!
                            2 -> data.keys.filter { it.y == pos.y }.maxByOrNull { it.x }!!
                            3 -> data.keys.filter { it.x == pos.x }.maxByOrNull { it.y }!!
                            else -> throw Error("NO")
                        }
                    }
                    if (data[next]!!) pos = next
                }
            }
        }

        return 1000 * pos.y + 4 * pos.x + rotation
    }

    override fun part2(): Any {
        return -1
    }
}