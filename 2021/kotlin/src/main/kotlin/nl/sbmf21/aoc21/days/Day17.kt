package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.mapToInts
import nl.sbmf21.math.Vector2i
import kotlin.math.sign

class Day17 : Day() {

    private val target = input[0].removePrefix("target area: ").split(", ").run {
        val x = this[0].removePrefix("x=").split("..").mapToInts()
        val y = this[1].removePrefix("y=").split("..").mapToInts()
        ProbeTarget(x[0], x[1], y[0], y[1])
    }
    private val successfulVelocities = bruteForce()

    override fun part1() = successfulVelocities.maxOf { it.value }
    override fun part2() = successfulVelocities.size

    private fun bruteForce() = mutableListOf<Vector2i>()
        .also { for (x in 0..target.maxX) for (y in target.minY..-target.minY) it.add(Vector2i(x, y)) }
        .fold(mutableMapOf<Probe, Int>()) { successful, velocity ->
            val probe = Probe(velocity = velocity)
            val positions = mutableSetOf(probe.pos)
            while (!probe.isPast(target) && !probe.isIn(target)) positions.add(probe.step().pos)
            successful.also { if (probe.isIn(target)) it[probe] = positions.maxOf { p -> p.y } }
        }

    private data class ProbeTarget(val minX: Int, val maxX: Int, val minY: Int, val maxY: Int)

    private data class Probe(var pos: Vector2i = Vector2i(0, 0), var velocity: Vector2i) {
        fun step() = also { pos = pos + velocity; velocity = velocity + Vector2i(-velocity.x.sign, -1) }
        fun isIn(t: ProbeTarget) = pos.x in t.minX..t.maxX && pos.y in t.minY..t.maxY
        fun isPast(t: ProbeTarget) = velocity.x == 0 && pos.x < t.minX || pos.x > t.maxX || pos.y < t.minY
    }
}