package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.split
import nl.sbmf21.aoc.common.splitToLongs
import nl.sbmf21.aoc.common.subList
import java.lang.Long.min
import kotlin.math.max

class Day5 : Day() {

    private val seeds = input[0].substringAfter(": ").splitToLongs(" ")
    private val maps = input.subList(1)
        .split(predicate = String::isBlank)
        .map { a -> a.subList(1).map { l -> l.splitToLongs(" ").let { (d, s, l) -> AlmanacMap(d, s, l) } } }

    override fun part1() = seeds.minOf { seed ->
        var value = seed
        maps.forEach { map -> value = find(map, value) }
        value
    }

    override fun part2(): Long {
        return maps.fold(seeds.chunked(2).map { (s, e) -> s to s + e }) { min, map -> yeet(min, map) }
            .filter { it.first > 0 }
            .minOf { it.first }
    }

    private fun yeet(min: List<Pair<Long, Long>>, map: List<AlmanacMap>): MutableList<Pair<Long, Long>> {
        val result = mutableListOf<Pair<Long, Long>>()

        for ((destStart, srcStart, range) in map) {
            val segments = buildList {
                for ((minSeed, maxSeed) in min) {
                    val src = srcStart to srcStart + range
                    val check = minSeed to maxSeed

                    if (check.first > src.second || src.first > check.second) continue
                    add(max(src.first, check.first) to min(src.second, check.second))
                }
            }

            var start = srcStart
            var end: Long

            segments.forEach {
                end = it.first
                if (start < end) result.add(start to end - 1)
                result.add(destStart - srcStart + it.first to destStart - srcStart + it.second - 1)
                start = it.second
            }
            end = start + range
            if (start < end) result.add(start to end - 1)
        }

        return result
    }

    private fun find(map: List<AlmanacMap>, check: Long): Long {
        map.forEach {
            val (dest, src, range) = it
            if (check >= src && check <= src + range) return dest - src + check
        }
        return check
    }

    private class AlmanacMap(val destinationRangeStart: Long, val sourceRangeStart: Long, val rangeLength: Long) {
        operator fun component1() = destinationRangeStart
        operator fun component2() = sourceRangeStart
        operator fun component3() = rangeLength
    }
}