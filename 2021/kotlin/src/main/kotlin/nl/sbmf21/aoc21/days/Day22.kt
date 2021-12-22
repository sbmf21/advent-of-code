package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc.common.Vector3
import kotlin.math.max
import kotlin.math.min

class Day22(input: List<String>) : ADay(input) {

    private val rebootRegex = Regex("(?<s>o(n|ff)) x=(?<x1>-?\\d+)..(?<x2>-?\\d+),y=(?<y1>-?\\d+)..(?<y2>-?\\d+),z=(?<z1>-?\\d+)..(?<z2>-?\\d+)")
    private val rebootSteps = input.map { rebootRegex.matchEntire(it)!! }.map {
        RebootStep(
            it.groups["s"]!!.value == "on",
            it.toInt("x1"),
            it.toInt("x2"),
            it.toInt("y1"),
            it.toInt("y2"),
            it.toInt("z1"),
            it.toInt("z2"),
        )
    }

    override fun part1() = rebootSteps.fold(mutableMapOf<Vector3, Boolean>()) { m, it ->
        for (x in it.bx) for (y in it.by) for (z in it.bz) m[Vector3(x, y, z)] = it.state; m
    }.count { it.value }

    override fun part2() = -1L
}

internal data class RebootStep(
    val state: Boolean,
    val minX: Int,
    val maxX: Int,
    val minY: Int,
    val maxY: Int,
    val minZ: Int,
    val maxZ: Int,
) {
    val bx = max(minX, -50)..min(maxX, 50)
    val by = max(minY, -50)..min(maxY, 50)
    val bz = max(minZ, -50)..min(maxZ, 50)
    val x = minX..maxX
    val y = minY..maxY
    val z = minZ..maxZ
}

