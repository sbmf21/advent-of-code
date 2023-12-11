package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Color
import nl.sbmf21.aoc.common.Day
import nl.sbmf21.math.Vector2i
import nl.sbmf21.math.by

class Day10 : Day() {

    private val map = buildSet seen@{
        val start = run {
            val y = input.indexOfFirst { it.contains('S') }
            val x = input[y].indexOf('S')
            x by y
        }

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

    private fun neighbours(char: Char) = when (char) {
        NW -> listOf(0 by -1, -1 by 0)
        NE -> listOf(0 by -1, 1 by 0)
        SW -> listOf(0 by 1, -1 by 0)
        SE -> listOf(0 by 1, 1 by 0)
        NS -> listOf(0 by -1, 0 by 1)
        EW -> listOf(-1 by 0, 1 by 0)
        else -> throw Error()
    }

    private fun neighbours1() = listOf(
        -1 by -1,
        -1 by 0,
        -1 by 1,
        0 by -1,
        0 by 1,
        1 by -1,
        1 by 0,
        1 by 1,
    )

    override fun part2(): Any {

        val starts = buildSet {
            input.forEachIndexed { y, line ->
                if (y == 0 || y == input.lastIndex) {
                    for (x in line.indices) {
                        this += x by y
                    }
                } else {
                    this += 0 by y
                    this += line.lastIndex by y
                }
            }
        }.filter { it !in map }

        val fillSeen = mutableSetOf<Vector2i>()
        val filled = starts.toMutableSet()
        var fillNext = starts.flatMap { neighbours1().map { n -> n + it } }.toMutableSet()

        while (fillNext.isNotEmpty()) {
            val checks = fillNext
                .filter { it !in fillSeen && it.y in 0..input.lastIndex && it.x in 0..input[it.y].lastIndex }

            val cnext = mutableSetOf<Vector2i>()

            checks.forEach {
                fillSeen += it
                if (it !in map) filled += it
                neighbours1().map { n -> n + it }.forEach { n ->
                    if (n !in fillSeen && n !in map) {
                        cnext += n
                    }
                }
            }

            fillNext = cnext
        }

        fillNext = findMoreToCheck(filled).toMutableSet()

        while (fillNext.isNotEmpty()) {

            fillNext.forEach {
                followMap(it)
            }

        }

        input.mapIndexed { y, line ->
            line.mapIndexed { x, c ->
                val pos = x by y
                if (pos in filled) {
                    'O'
                } else if (input[y][x] == '.') {
                    Color.BOLD + "I" + Color.RESET
                } else {
                    input[y][x]
                }
            }.joinToString("")
        }.joinToString("\n").let(::println)

        return input.sumOf { it.length } - filled.size - map.size
    }

    fun followMap(p: Vector2i) {

        neighbours1().map { p + it }.filter {

        }

    }

    fun findType(s: Vector2i): Char {
        val t = try {
            input[s.y - 1][s.x]
        } catch (_: Throwable) {
            '.'
        }

        val b = try {
            input[s.y + 1][s.x]
        } catch (_: Throwable) {
            '.'
        }
        val l = try {
            input[s.y][s.x - 1]
        } catch (_: Throwable) {
            '.'
        }

        val r = try {
            input[s.y][s.x + 1]
        } catch (_: Throwable) {
            '.'
        }

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

    fun findMoreToCheck(filled: Set<Vector2i>) = map
        .flatMap { neighbours1().map { n -> it + n } }
        .filter { it !in map }
        .filter { it !in filled }

//    fun canMoveOutFrom(pos:Vector2i, filled:Set<Vector2i>):Boolean {
//
//    }

    companion object {
        val NW = 'J' // ┘
        val NE = 'L' // └
        val SW = '7' // ┐
        val SE = 'F' // ┌
        val NS = '|'
        val EW = '-'

        val TOP_L = listOf(SW, SE, NS)
        val BOTTOM_L = listOf(NE, NW, NS)
        val LEFT_L = listOf(NE, SE, EW)
        val RIGHT_L = listOf(NW, SW, EW)
    }
}