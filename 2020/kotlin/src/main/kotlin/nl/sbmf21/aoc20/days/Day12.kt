package nl.sbmf21.aoc20.days

import nl.sbmf21.aoc.common.ADay
import java.util.regex.Pattern
import kotlin.math.abs

class Day12(input: List<String>) : ADay(input) {

    private val pattern = Pattern.compile("(?<action>[NSEWLRF])(?<value>\\d+)")
    private val instructions = input.map {
        val matcher = pattern.matcher(it)
        matcher.matches()
        Pair(matcher.group("action"), matcher.group("value").toInt())
    }

    override fun part1(): Int {

        var ship = Pair(0, 0) // x y
        var direction = 1

        instructions.forEach {
            when (it.first) {
                "L" -> direction = rotateDirection(direction, -it.second)
                "R" -> direction = rotateDirection(direction, it.second)
                "F" -> ship = moveInDirection(ship, direction, it.second)
                "N" -> ship = moveInDirection(ship, 0, it.second)
                "S" -> ship = moveInDirection(ship, 2, it.second)
                "E" -> ship = moveInDirection(ship, 1, it.second)
                "W" -> ship = moveInDirection(ship, 3, it.second)
            }
        }

        return toManhattan(ship)
    }

    override fun part2(): Int {
        var waypoint = Pair(10, 1) // x y east 10 north 1
        var ship = Pair(0, 0)

        instructions.forEach {
            when (it.first) {
                "L" -> waypoint = rotateWaypoint(waypoint, -it.second)
                "R" -> waypoint = rotateWaypoint(waypoint, it.second)
                "F" -> ship = moveShipToWaypoint(ship, waypoint, it.second)
                "N" -> waypoint = moveInDirection(waypoint, 0, it.second)
                "S" -> waypoint = moveInDirection(waypoint, 2, it.second)
                "E" -> waypoint = moveInDirection(waypoint, 1, it.second)
                "W" -> waypoint = moveInDirection(waypoint, 3, it.second)
            }
        }

        return toManhattan(ship)
    }

    private fun rotateDirection(direction: Int, value: Int): Int {
        val newDirection = (direction + value / 90) % 4
        return if (newDirection < 0) newDirection + 4 else newDirection
    }

    private fun rotateWaypoint(waypoint: Pair<Int, Int>, value: Int): Pair<Int, Int> {
        var dir = value / 90 % 4
        if (dir < 0) dir += 4

        return when (dir) {
            1 -> Pair(waypoint.second, -waypoint.first)
            2 -> Pair(-waypoint.first, -waypoint.second)
            3 -> Pair(-waypoint.second, waypoint.first)
            else -> waypoint
        }
    }

    private fun moveShipToWaypoint(ship: Pair<Int, Int>, waypoint: Pair<Int, Int>, value: Int) = Pair(
        ship.first + waypoint.first * value,
        ship.second + waypoint.second * value,
    )

    private fun moveInDirection(point: Pair<Int, Int>, direction: Int, value: Int) = when (direction) {
        0 -> point.copy(second = point.second + value)
        2 -> point.copy(second = point.second - value)
        1 -> point.copy(first = point.first + value)
        3 -> point.copy(first = point.first - value)
        else -> point
    }

    private fun toManhattan(point: Pair<Int, Int>) = abs(point.first) + abs(point.second)
}
