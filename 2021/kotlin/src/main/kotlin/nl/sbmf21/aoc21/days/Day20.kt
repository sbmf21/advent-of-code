package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.math.Vector2i

class Day20 : Day() {

    private val algo = input[0].map { if (it == '#') 1 else 0 }
    private val img = input.subList(2, input.size).map { r -> r.map { if (it == '#') 1 else 0 } }
    private val neigh = (-1..1).map { y -> (-1..1).map { x -> Vector2i(x, y) } }.flatten()

    override fun part1() = run(2)
    override fun part2() = run(50)

    private fun run(times: Int) = (1..times)
        .fold(img) { img, i -> List(img.size + 2) { y -> MutableList(img.size + 2) { x -> check(y, x, img, i) } } }
        .sumOf { r -> r.count { it == 1 } }

    private fun check(y: Int, x: Int, img: List<List<Int>>, pass: Int) = algo[neigh.map { Vector2i(x - 1, y - 1) + it }
        .map { if (it.y in img.indices && it.x in img[it.y].indices) img[it.y][it.x] else algo[if (algo[0] == 1) pass % 2 * algo.lastIndex else 0] }
        .fold(0) { acc, p -> (acc shl 1) + p }]
}