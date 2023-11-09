package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc.common.mapToInts
import nl.sbmf21.math.Vector3i as vec

class Day18(input: List<String>) : ADay(input) {

    var store: ((Set<vec>, Set<vec>, Int, Int, Int, Int, Int, Int) -> Unit)? = null
    private val droplets = input.map { it.split(",").mapToInts() }.mapTo(mutableSetOf()) { vec(it[0], it[1], it[2]) }
    private val neighbours = listOf(
        vec(1, 0, 0), vec(-1, 0, 0),
        vec(0, 1, 0), vec(0, -1, 0),
        vec(0, 0, 1), vec(0, 0, -1),
    )

    override fun part1() = droplets.sumOf { droplet -> 6 - neighbours.map { droplet + it }.count { it in droplets } }

    override fun part2(): Int {

        val (maxX, minX) = droplets.maxOf { it.x } + 1 to droplets.minOf { it.x } - 1
        val (maxY, minY) = droplets.maxOf { it.y } + 1 to droplets.minOf { it.y } - 1
        val (maxZ, minZ) = droplets.maxOf { it.z } + 1 to droplets.minOf { it.z } - 1

        val steamMap = mutableSetOf<vec>()
        val points = mutableListOf(
            vec(maxX, maxY, maxZ), vec(minX, maxY, maxZ),
            vec(maxX, minY, maxZ), vec(minX, minY, maxZ),
            vec(maxX, maxY, minZ), vec(minX, maxY, minZ),
            vec(maxX, minY, minZ), vec(minX, minY, minZ),
        )

        store?.invoke(droplets, steamMap, maxX, minX, maxY, minY, maxZ, minZ)

        while (points.isNotEmpty()) {
            val point = points.removeFirst()
            steamMap.add(point)
            store?.invoke(droplets, steamMap, maxX, minX, maxY, minY, maxZ, minZ)
            points.addAll(neighbours.map { point + it }.filter {
                it.x in minX..maxX && it.y in minY..maxY && it.z in minZ..maxZ && it !in steamMap && it !in points && it !in droplets
            })
        }

        return steamMap.sumOf { steam -> neighbours.map { steam + it }.count { it in droplets } }
    }
}