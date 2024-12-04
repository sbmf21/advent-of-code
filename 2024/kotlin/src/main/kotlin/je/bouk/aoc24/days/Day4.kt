package je.bouk.aoc24.days

import je.bouk.aoc24.days.Day4.Direction.*
import nl.sbmf21.aoc.common.Day

class Day4 : Day() {

    private val ws = input.map(String::toCharArray)
    private val crosses = mapOf(
        NE to listOf(SE, NW),
        SW to listOf(SE, NW),
        SE to listOf(NE, SW),
        NW to listOf(NE, SW),
    )

    override fun part1() = search("XMAS", Direction.entries).size

    override fun part2() = search("MAS", crosses.keys)
        .let { it.filter { a -> it.any { b -> crosses(a, b) } } }
        .size / 2

    private fun search(search: String, directions: Iterable<Direction>) = buildSet {
        val chars = search.toCharArray()
        for (y in ws.indices) for (x in ws[y].indices) for (d in directions) {
            find(x, y, d, chars)?.let(this::add)
        }
    }

    private fun find(x: Int, y: Int, dir: Direction, chars: CharArray): Result? = Result(chars.mapIndexed { i, c ->
        val cx = x + (dir.x * i)
        val cy = y + (dir.y * i)
        if (cy !in ws.indices || cx !in ws[cy].indices || ws[cy][cx] != c) return null
        cx to cy
    }, dir)

    private fun crosses(a: Result, b: Result) = a.dir in crosses[b.dir]!! && b.pos[1] == a.pos[1]

    private data class Result(val pos: List<Pair<Int, Int>>, val dir: Direction)

    private enum class Direction(val x: Int, val y: Int) {
        N(0, -1),
        S(0, 1),
        E(1, 0),
        W(-1, 0),
        NE(1, -1),
        SW(-1, 1),
        SE(1, 1),
        NW(-1, -1);
    }
}