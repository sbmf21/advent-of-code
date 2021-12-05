package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay
import java.lang.Integer.max
import kotlin.math.sign

class Day5(input: List<String>) : ADay(input) {

    private val pattern = Regex("(?<x1>\\d+),(?<y1>\\d+)\\s+->\\s+(?<x2>\\d+),(?<y2>\\d+)")
    private val lines = input.map { pattern.matchEntire(it)!!.groups }
        .map { Line(i(it["x1"]), i(it["y1"]), i(it["x2"]), i(it["y2"])) }

    override fun part1() = run(lines.filter { it.x1 == it.x2 || it.y1 == it.y2 })

    override fun part2() = run()

    private fun run(l: List<Line> = this.lines) = l.fold(Array(l.maxX()) { IntArray(l.maxY()) }) { m, v ->
        val p = Vec(v.x1, v.y1)
        while (true) {
            m[p.x][p.y]++
            if ((p.x == v.x2 && p.y == v.y2)) break
            p.x += v.step.x; p.y += v.step.y
        }; m
    }.sumOf { it.filter { p -> p >= 2 }.size }
}

data class Vec<N : Number>(var x: N, var y: N)
data class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {

    val step = Vec(x2 - x1, y2 - y1).step()
    val maxX = max(x1, x2)
    val maxY = max(y1, y2)
}

private fun i(m: MatchGroup?) = m!!.value.toInt()
private fun List<Line>.maxX() = map { it.maxX }.maxOf { it } + 1
private fun List<Line>.maxY() = map { it.maxY }.maxOf { it } + 1
private fun Vec<Int>.step() = Vec(x.sign, y.sign)
