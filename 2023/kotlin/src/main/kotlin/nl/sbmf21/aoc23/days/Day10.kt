package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.math.Vector2i
import nl.sbmf21.math.by

class Day10 : Day() {

    private val start = run {
        val y = input.indexOfFirst { it.contains('S') }
        input[y].indexOf('S') by y
    }

    private val map = buildSet seen@{
        this += start
        var next = neighbours(findType(start)).map { start + it }.toSet()

        while (next.isNotEmpty()) next = buildSet next@{
            next.map { neighbours(input[it.y][it.x]).map { n -> n + it } }.forEach { n ->
                n.forEach {
                    if (it !in this@seen && it.y in 0..input.lastIndex && it.x in 0..input[it.y].lastIndex) {
                        this@next += it
                        this@seen += it
                    }
                }
            }
        }
    }

    override fun part1() = map.size / 2

    override fun part2(): Int {
        val start = map.sortedBy { it.x }.sortedBy { it.y }.first { input[it.y][it.x] == EW }
        var pos = start

        val checks = buildSet {
            var dir = RIGHT
            var check = DOWN

            while (true) {
                val next = pos + dir
                if (next == start) break

                val (newDir, newCheck) = rotate(at(next), dir, check)

                (pos + check).let { if (it !in map) this += it }
                if (dir != newDir) (next + check).let { if (it !in map) this += it }

                dir = newDir
                check = newCheck
                pos = next
            }
        }

        return buildSet {
            addAll(checks)
            checks.forEach { current ->
                var next = listOf(current)
                while (next.isNotEmpty()) next = next.flatMap { c -> NEIGHBORS.map { c + it } }
                    .toSet()
                    .filter { it !in this }
                    .filter { it !in map }
                    .also { this.addAll(it) }
            }
        }.size
    }

    private fun neighbours(char: Char) = when (char) {
        NW -> listOf(UP, LEFT)
        NE -> listOf(UP, RIGHT)
        SW -> listOf(DOWN, LEFT)
        SE -> listOf(DOWN, RIGHT)
        NS -> listOf(UP, DOWN)
        EW -> listOf(LEFT, RIGHT)
        else -> throw Error()
    }

    private fun rotate(next: Char, dir: Vector2i, check: Vector2i) = when (dir) {
        UP -> when (next) {
            SE -> RIGHT to DOWN
            SW -> LEFT to UP
            NS -> dir to check
            else -> throw Error("Unexpected up $next")
        }

        DOWN -> when (next) {
            NE -> RIGHT to DOWN
            NW -> LEFT to UP
            NS -> dir to check
            else -> throw Error("Unexpected down $next")
        }

        LEFT -> when (next) {
            NE -> UP to RIGHT
            SE -> DOWN to LEFT
            EW -> dir to check
            else -> throw Error("Unexpected left $next")
        }

        RIGHT -> when (next) {
            NW -> UP to RIGHT
            SW -> DOWN to LEFT
            EW -> dir to check
            else -> throw Error("Unexpected right $next")
        }

        else -> throw Error("Unexpected direction ${dir.x} ${dir.y}")
    }

    private fun findType(s: Vector2i): Char {
        val t = at(s + UP)
        val b = at(s + DOWN)
        val l = at(s + LEFT)
        val r = at(s + RIGHT)

        return when {
            t in TOP_L && b in BOTTOM_L -> NS
            l in LEFT_L && r in RIGHT_L -> EW
            r in RIGHT_L && b in BOTTOM_L -> SE
            r in RIGHT_L && t in TOP_L -> NE
            l in LEFT_L && b in BOTTOM_L -> SW
            l in LEFT_L && t in TOP_L -> NW
            else -> throw Error()
        }
    }

    private fun at(pos: Vector2i): Char {
        val c = if (pos.y in input.indices && pos.x in input[pos.y].indices) input[pos.y][pos.x] else '.'
        return if (c == 'S') findType(pos) else c
    }

    private companion object {
        val RIGHT = 1 by 0
        val LEFT = -1 by 0
        val DOWN = 0 by 1
        val UP = 0 by -1

        val NEIGHBORS = listOf(UP, DOWN, LEFT, RIGHT)

        const val NW = 'J'
        const val NE = 'L'
        const val SW = '7'
        const val SE = 'F'
        const val NS = '|'
        const val EW = '-'

        val TOP_L = listOf(SW, SE, NS)
        val BOTTOM_L = listOf(NE, NW, NS)
        val LEFT_L = listOf(NE, SE, EW)
        val RIGHT_L = listOf(NW, SW, EW)
    }
}