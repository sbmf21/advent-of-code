package je.bouk.aoc24.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.util.Direction

class Day4 : Day() {

    private val ws = input.map(String::toCharArray)

    override fun part1() = search("XMAS", Direction.all()).size

    override fun part2() = search("MAS", Direction.diagonal())
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

    private fun crosses(a: Result, b: Result) = a.dir in b.dir.perpendicular && b.pos[1] == a.pos[1]

    private data class Result(val pos: List<Pair<Int, Int>>, val dir: Direction)
}