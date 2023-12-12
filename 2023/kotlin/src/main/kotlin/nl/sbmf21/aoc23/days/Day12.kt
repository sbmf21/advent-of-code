package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Color
import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.mapToInts

class Day12 : Day() {

    private val data = input.map {
        val parts = it.split(" ")

        val left = parts[0]
        val right = parts[1].split(",").mapToInts()

        left to right
    }

    override fun part1() = data.sumOf { (corrupted, counts) ->
        val indices = corrupted.withIndex().filter { it.value == '?' }.map { it.index }
        val combinations = combinations(listOf("#", "."), indices.size)

        buildList {
            combinations.forEach { c ->
                add(buildList {
                    val a = c.mapIndexed { index, c -> indices[index] to c }.toMap()

                    var current = 0

                    for (i in corrupted.indices) when (if (i in a) a[i] else corrupted[i]) {
                        '.' -> if (current > 0) {
                            add(current)
                            current = 0
                        }

                        '#' -> current++
                    }

                    if (current > 0) add(current)
                })
            }
        }.count { isValid(it, counts) }
    }

    override fun part2(): Any {
        // ... yeah, nah
        return Color.RED + "# TODO #" + Color.RESET
    }

    private fun isValid(groups: List<Int>, counts: List<Int>): Boolean {
        if (groups.size != counts.size) return false
        for (i in counts.indices) if (groups[i] != counts[i]) return false
        return true
    }

    private fun combinations(elements: List<String>, lengthOfList: Int): List<String> =
        if (lengthOfList == 1) elements
        else buildList {
            val subs = combinations(elements, lengthOfList - 1)
            for (i in elements.indices) for (j in subs.indices) {
                this += elements[i] + subs[j]
            }
        }
}