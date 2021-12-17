package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc.common.Vector2
import nl.sbmf21.aoc.common.mapToInts
import kotlin.math.sign

class Day17(input: List<String>) : ADay(input) {

    private val target = input[0].removePrefix("target area: ").split(", ").run {
        val x = this[0].removePrefix("x=").split("..").mapToInts()
        val y = this[1].removePrefix("y=").split("..").mapToInts()
        ProbeTarget(x[0], x[1], y[0], y[1])
    }
    private val successfulVelocities = bruteForce()

    override fun part1() = successfulVelocities.maxOf { it.value }
    override fun part2() = successfulVelocities.size

    private fun bruteForce() = mutableListOf<Vector2>()
        .also { for (x in 0..target.maxX) for (y in target.minY..-target.minY) it.add(Vector2(x, y)) }
        .fold(mutableMapOf<Probe, Int>()) { successful, velocity ->
            val probe = Probe(velocity = velocity)
            val positions = mutableSetOf(probe.pos)
            while (!probe.isPast(target) && !probe.isIn(target)) positions.add(probe.step().pos)
            successful.also { if (probe.isIn(target)) it[probe] = positions.maxOf { p -> p.y } }
        }
}

internal data class Probe(var pos: Vector2 = Vector2(0, 0), var velocity: Vector2) {
    fun step() = also { pos += velocity; velocity += Vector2(-velocity.x.sign, -1) }
    fun isIn(target: ProbeTarget) = pos.x in target.minX..target.maxX && pos.y in target.minY..target.maxY
    fun isPast(target: ProbeTarget) = (velocity.x == 0 && pos.x < target.minX)
        || pos.x > target.maxX || pos.y < target.minY
}

internal data class ProbeTarget(val minX: Int, val maxX: Int, val minY: Int, val maxY: Int)
