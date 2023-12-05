package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.mapToLongs
import java.lang.Long.min
import kotlin.math.max

class Day5 : Day() {

    class Seed(
        val id: Long,
        var soil: Long = 0,
        var fertilizer: Long = 0,
        var water: Long = 0,
        var light: Long = 0,
        var temperature: Long = 0,
        var humidity: Long = 0,
        var location: Long = 0,
    )

    override fun part1(): Any {

        val seeds = input[0].split(": ")[1].split(" ").mapToLongs().map { Seed(id = it) }

        val maps = input.subList(2, input.size).joinToString("\n").split("\n\n").associate {
            val lines = it.split("\n")
            val name = lines[0].split(" ")[0]

            val map = lines.subList(1, lines.size).map { l ->
                l.split(" ").mapToLongs()
            }

            name to map
        }

        seeds.forEach { seed ->
            seed.soil = find(maps["seed-to-soil"]!!, seed.id)
            seed.fertilizer = find(maps["soil-to-fertilizer"]!!, seed.soil)
            seed.water = find(maps["fertilizer-to-water"]!!, seed.fertilizer)
            seed.light = find(maps["water-to-light"]!!, seed.water)
            seed.temperature = find(maps["light-to-temperature"]!!, seed.light)
            seed.humidity = find(maps["temperature-to-humidity"]!!, seed.temperature)
            seed.location = find(maps["humidity-to-location"]!!, seed.humidity)
        }

        return seeds.minOf { it.location }
    }

    private fun find(map: List<List<Long>>, check: Long): Long {
        map.forEach {
            val (dest, src, range) = it
            if (check >= src && check <= src + range) {
                return dest - src + check
            }
        }

        return check
    }

    override fun part2(): Any {
        return -1L

        val seeds = input[0].split(": ")[1].split(" ").mapToLongs().chunked(2).map { it[0] to it[0] + it[1] }
        val maps = input.subList(2, input.size).joinToString("\n").split("\n\n").associate {
            val lines = it.split("\n")
            val name = lines[0].split(" ")[0]

            val map = lines.subList(1, lines.size).map { l ->
                l.split(" ").mapToLongs()
            }

            name to map
        }

        var min = seeds

        maps.forEach { (s, map) ->
            val result = mutableListOf<Pair<Long, Long>>()

            for ((dest, src, range) in map) {
                for ((minSeed, maxSeed) in min) {
                    val l = src to src + range
                    val r = minSeed to maxSeed

                    if(l.first > r.second) {
//                        result.add(l)
//                        continue
                    }else if(l.second< r.first) {
//                        result.add(r)
                    } else {
                        val out = dest - src + max(l.first, r.first) to dest - src + min(l.second, r.second)
                        result.add(out)
                    }
                }
            }

            min = result
        }

        return min.map { it.first }.min()
    }
}