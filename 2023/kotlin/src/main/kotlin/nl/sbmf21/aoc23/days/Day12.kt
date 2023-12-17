package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.splitToInts

class Day12 : Day() {

    private val data = input.map { it.split(" ").let { (segment, groups) -> segment to groups.splitToInts(",") } }

    override fun part1() = solve()

    override fun part2() = solve(data.map { (segment, groups) ->
        List(5) { segment }.joinToString("?") to List(5) { groups }.flatten()
    })

    private fun solve(data: List<Pair<String, List<Int>>> = this.data) = data.sumOf { (parts, nums) ->
        arrangements("$parts.", nums, mutableMapOf())
    }

    private fun arrangements(segment: String, groups: List<Int>, results: MutableMap<Pair<Int, Int>, Long>): Long {
        val damageCount = groups.sum()
        val minDamage = segment.count { it == '#' }
        val maxDamage = minDamage + segment.count { it == '?' }

        return when {
            damageCount == 0 && minDamage == 0 -> 1
            damageCount !in minDamage..maxDamage -> 0

            segment[0] == '?' -> {
                val index = segment.length to groups.size
                if (index !in results) results[index] = arrangements(segment.substring(1), groups, results).let { c ->
                    if (segment.substring(0, groups[0]).all { it != '.' } && segment[groups[0]] != '#')
                        c + arrangements(segment.substring(groups[0] + 1), groups.drop(1), results)
                    else c
                }
                results[index]!!
            }

            segment[0] == '#' -> {
                if (segment.substring(0, groups[0]).all { it == '#' || it == '?' } && segment[groups[0]] != '#')
                    arrangements(segment.substring(groups[0] + 1), groups.drop(1), results)
                else 0
            }

            else -> arrangements(segment.substring(1), groups, results)
        }
    }
}