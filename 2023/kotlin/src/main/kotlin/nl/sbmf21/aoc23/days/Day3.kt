package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.prod
import nl.sbmf21.math.Vector2i
import nl.sbmf21.math.by
import kotlin.math.max
import kotlin.math.min

class Day3 : Day() {

    private val parts = buildList {
        var currentNum = ""
        var ids = mutableListOf<Int>()

        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                if (c in NUMBERS) {
                    currentNum += c
                    ids += x
                } else if (currentNum !== "") {
                    add(Triple(currentNum.toInt(), y, ids.toList()))
                    currentNum = ""
                    ids = mutableListOf()
                }
            }
        }
    }

    override fun part1() = buildList {
        run { n, x, y ->
            if (input[y][x] !in NUMBERS && input[y][x] != NO_SYMBOL) {
                add(n)
            }
        }
    }.sum()

    override fun part2() = buildMap<Vector2i, MutableList<Int>> {
        run { n, x, y ->
            if (input[y][x] == GEAR) {
                val gear = x by y

                if (gear !in this) this[gear] = mutableListOf()
                this[gear]!!.add(n)
            }
        }
    }.values.filter { it.size == 2 }.sumOf { it.prod() }

    private fun run(handle: (Int, Int, Int) -> Unit) {
        parts.forEach { (num, y, x) ->
            val minY = max(0, y - 1)
            val maxY = min(input.lastIndex, y + 1)
            val minX = max(0, x.min() - 1)
            val maxX = min(input[y].lastIndex, x.max() + 1)

            for (cy in minY..maxY) {
                for (cx in minX..maxX) {
                    if (cy == y && cx in x) continue
                    handle(num, cx, cy)
                }
            }
        }
    }

    private companion object {
        val NUMBERS = '0'..'9'
        const val NO_SYMBOL = '.'
        const val GEAR = '*'
    }
}