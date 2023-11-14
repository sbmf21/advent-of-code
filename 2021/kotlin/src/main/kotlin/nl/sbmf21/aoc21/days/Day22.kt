package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.math.Vector3l
import kotlin.math.max
import kotlin.math.min

class Day22 : Day() {

    private val rebootRegex =
        Regex("(?<s>o(n|ff)) x=(?<x1>-?\\d+)..(?<x2>-?\\d+),y=(?<y1>-?\\d+)..(?<y2>-?\\d+),z=(?<z1>-?\\d+)..(?<z2>-?\\d+)")
    private val steps = input.map { rebootRegex.matchEntire(it)!! }.map {
        RebootStep(
            it.groups["s"]!!.value == "on",
            it.toLong("x1"),
            it.toLong("x2"),
            it.toLong("y1"),
            it.toLong("y2"),
            it.toLong("z1"),
            it.toLong("z2"),
        )
    }

    override fun part1() = steps.fold(mutableMapOf<Vector3l, Boolean>()) { map, it ->
        for (x in it.x) for (y in it.y) for (z in it.z) map[Vector3l(x, y, z)] = it.state
        map
    }.count { it.value }

    override fun part2() = steps
        .fold(mutableListOf<Cube>()) { map, step ->
            val cubes = map.map { it.mapIntersections(step.cube) }.flatten().toMutableList()
            if (step.state) cubes += step.cube
            cubes
        }.sumOf { it.size }

    private data class Cube(
        val minX: Long,
        val maxX: Long,
        val minY: Long,
        val maxY: Long,
        val minZ: Long,
        val maxZ: Long,
    ) {
        val size get() = (maxX - minX) * (maxY - minY) * (maxZ - minZ)

        fun mapIntersections(other: Cube): List<Cube> {
            if (other.contains(this)) return listOf()
            if (!intersects(other)) return listOf(this)

            val cubes = mutableListOf<Cube>()
            val x = findOutsideX(other)
            val y = findOutsideY(other)
            val z = findOutsideZ(other)

            for (ix in 0 until x.size - 1) for (iy in 0 until y.size - 1) for (iz in 0 until z.size - 1)
                cubes.add(Cube(x[ix], x[ix + 1], y[iy], y[iy + 1], z[iz], z[iz + 1]))

            return cubes.filter { !other.contains(it) }
        }

        private fun findOutsideX(other: Cube) = findOutside(this, other, { it.minX }, { it.maxX })
        private fun findOutsideY(other: Cube) = findOutside(this, other, { it.minY }, { it.maxY })
        private fun findOutsideZ(other: Cube) = findOutside(this, other, { it.minZ }, { it.maxZ })

        private fun contains(other: Cube) = minX <= other.minX && maxX >= other.maxX
            && minY <= other.minY && maxY >= other.maxY
            && minZ <= other.minZ && maxZ >= other.maxZ

        private fun intersects(other: Cube) = minX <= other.maxX && maxX >= other.minX
            && minY <= other.maxY && maxY >= other.minY
            && minZ <= other.maxZ && maxZ >= other.minZ

        private fun findOutside(left: Cube, right: Cube, min: (c: Cube) -> Long, max: (c: Cube) -> Long) = listOf(
            min(left),
            *listOf(min(right), max(right))
                .filter { min(left) < it && max(left) > it }
                .toTypedArray(),
            max(left),
        )
    }

    private data class RebootStep(
        val state: Boolean,
        val minX: Long,
        val maxX: Long,
        val minY: Long,
        val maxY: Long,
        val minZ: Long,
        val maxZ: Long,
    ) {
        val x = max(minX, -50)..min(maxX, 50)
        val y = max(minY, -50)..min(maxY, 50)
        val z = max(minZ, -50)..min(maxZ, 50)

        val cube = Cube(minX, maxX + 1, minY, maxY + 1, minZ, maxZ + 1)
    }
}