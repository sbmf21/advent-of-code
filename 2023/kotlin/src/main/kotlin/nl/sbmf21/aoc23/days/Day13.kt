package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.split
import nl.sbmf21.aoc23.TODO
import nl.sbmf21.math.Vector2i
import nl.sbmf21.math.by
import kotlin.math.max
import kotlin.math.min

class Day13 : Day() {

    private val patterns = input.split(true, String::isBlank).map { it to summarize(it)!! }

    override fun part1() = patterns.sumOf { (_, pos) -> summary(pos) }

    override fun part2(): String {
        return TODO

//        return patterns
//            .map { (map, original) ->
//                for (y in map.indices) {
//                    for (x in map[y].indices) {
//                        val testVal = summarize(flip(map, x, y), true)
//                        if (testVal != null && testVal != original) return@map testVal
//                    }
//                }
//
//                println("no change found")
//
//                original
//            }
//            .sumOf(::summary)
    }

    private fun summary(pos: Vector2i) = pos.x + 100 * pos.y

    private fun summarize(it: List<String>): Vector2i? {

        y@ for (i in 1..it.lastIndex) {
            if (it[i - 1] == it[i]) {
                val remaining = max(0, min(it.lastIndex - i, i - 1))

                for (ci in 0..remaining) {
                    if (it[i - 1 - ci] != it[i + ci]) continue@y
                }

                return 0 by i
            }
        }

        val verticals = buildList {
            for (i in it[0].indices) {
                add(it.joinToString("") { l -> "${l[i]}" })
            }
        }

        x@ for (i in 1..verticals.lastIndex) {
            if (verticals[i - 1] == verticals[i]) {
                val remaining = max(0, min(verticals.lastIndex - i, i - 1))

                for (ci in 0..remaining) {
                    if (verticals[i - 1 - ci] != verticals[i + ci]) continue@x
                }

                return i by 0
            }
        }

        return null
    }

//    private fun flip(map: List<String>, x: Int, y: Int) = map.mapIndexed { cy, line ->
//        if (cy == y) {
//            val next = when (line[x]) {
//                '#' -> '.'
//                '.' -> '#'
//                else -> throw Error("cannot flip")
//            }
//
//            line.substring(0..<x) + next + line.substring(x + 1..line.lastIndex)
//        } else line
//    }
}