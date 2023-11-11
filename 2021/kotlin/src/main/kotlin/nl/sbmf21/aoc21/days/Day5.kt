package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay
import java.lang.Integer.max
import kotlin.math.sign

class Day5(input: List<String>) : ADay(input) {

    private val pattern = Regex("(?<x1>\\d+),(?<y1>\\d+)\\s+->\\s+(?<x2>\\d+),(?<y2>\\d+)")
    private val lines = input.map { pattern.matchEntire(it)!! }
        .map { Line(it.toInt("x1"), it.toInt("y1"), it.toInt("x2"), it.toInt("y2")) }

    override fun part1() = run(lines.filter { it.x1 == it.x2 || it.y1 == it.y2 })

    override fun part2() = run()

    private fun run(l: List<Line> = this.lines) = l
        .fold(Array(l.maxOf { max(it.x1, it.x2) } + 1) { IntArray(l.maxOf { max(it.y1, it.y2) } + 1) }) { m, v ->
            val p = Vec(v.x1, v.y1)
            while (true) {
                m[p.x][p.y]++
                if ((p.x == v.x2 && p.y == v.y2)) break
                p.x += v.step.x; p.y += v.step.y
            }; m
        }.sumOf { it.filter { p -> p >= 2 }.size }

    private data class Vec<N : Number>(var x: N, var y: N)

    private data class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {
        val step = Vec((x2 - x1).sign, (y2 - y1).sign)
    }
}