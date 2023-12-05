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
        maps.forEach { map -> value = mapSeed(map, value) }
        value
    }

    override fun part2() = maps
        .fold(seeds.chunked(2).map { (s, e) -> s to s + e }, ::mapSeedRanges)
        .filter { it.first > 0 }
        .minOf { it.first }

    private fun mapSeed(map: List<AlmanacMap>, check: Long): Long {
        map.forEach {
            val (dest, src, range) = it
            if (check >= src && check <= src + range) return dest - src + check
        }
        return check
    }

    private fun mapSeedRanges(min: List<Pair<Long, Long>>, map: List<AlmanacMap>) = buildList {
        for ((minSeed, maxSeed) in min) {
            val seenSegments = map.mapNotNull { (destStart, srcStart, range) ->
                val src = srcStart to srcStart + range
                val check = minSeed to maxSeed

                if (check.first > src.second || src.first > check.second) return@mapNotNull null

                val overlap = max(src.first, check.first) to min(src.second, check.second)
                add(destStart - srcStart + overlap.first to destStart - srcStart + overlap.second)
                overlap
            }

            var start = minSeed
            var end: Long

            seenSegments.forEach {
                end = it.first
                if (start < end) add(start to end)
                start = it.second
            }

            end = maxSeed
            if (start < end) add(start to end)
        }
    }

    private class AlmanacMap(val destinationRangeStart: Long, val sourceRangeStart: Long, val rangeLength: Long) {
        operator fun component1() = destinationRangeStart
        operator fun component2() = sourceRangeStart
        operator fun component3() = rangeLength
    }
}