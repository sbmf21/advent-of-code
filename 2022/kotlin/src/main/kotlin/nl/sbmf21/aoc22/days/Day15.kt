package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc.common.mapToInts
import nl.sbmf21.aoc.common.subList
import nl.sbmf21.math.Vector2i
import nl.sbmf21.math.clamp
import kotlin.math.abs
import kotlin.math.max

class Day15(input: List<String>) : ADay(input) {

    private val regex = Regex(".*x=(-?\\d+), y=(-?\\d+).*x=(-?\\d+), y=(-?\\d+)")
    private val sensors: List<Pair<Vector2i, Vector2i>> = input
        .map { regex.find(it)!!.groupValues.subList(1).mapToInts() }
        .map { Vector2i(it[0], it[1]) to Vector2i(it[2], it[3]) }
        .fold(mutableListOf()) { sensors, map -> sensors.also { it.add(map) } }

    override fun part1() = sensors.fold(mutableMapOf<Int, Boolean>()) { row, (sensor, beacon) ->
        val (length, distance) = manhattan(sensor, beacon, ROW)
        if (beacon.y == ROW) row[beacon.x] = true
        for (x in sensor.x - length + distance..sensor.x + length - distance) if (x !in row) row[x] = false
        row
    }.count { (_, it) -> !it }

    override fun part2(): Any {
        (0..MAX_COORD).forEach { row ->
            var x = 0
            sensors.fold(mutableListOf<Pair<Int, Int>>()) { data, (sensor, beacon) ->
                val (length, distance) = manhattan(sensor, beacon, row)
                val start = clamp(sensor.x - (length - distance), 0, MAX_COORD)
                val end = clamp(sensor.x + (length - distance), 0, MAX_COORD) + 1
                data.also { it.add(start to end) }
            }.sortedBy { it.first }.forEach { (start, end) ->
                if (start > x) return (start - 1) * 4000000L + row
                x = max(x, end)
            }
        }
        return -1
    }

    private fun manhattan(left: Vector2i, right: Vector2i, row: Int) =
        abs(left.x - right.x) + abs(left.y - right.y) to abs(left.y - row)

    companion object {
        const val DEFAULT_ROW = 2_000_000
        var ROW = DEFAULT_ROW

        const val DEFAULT_MAX_COORD = 4_000_000
        var MAX_COORD = DEFAULT_MAX_COORD
    }
}