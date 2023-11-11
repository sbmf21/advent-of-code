package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay

class Day12(input: List<String>) : ADay(input) {

    private val options = input.asSequence()
        .map { it.split("-") }
        .map { listOf(Pair(Cave(it[0]), Cave(it[1])), Pair(Cave(it[1]), Cave(it[0]))) }
        .flatten()
        .filter { it.second.cave != "start" }
        .fold(mutableListOf<Cave>()) { acc, route ->
            if (acc.none { it == route.first }) acc.add(route.first)
            if (acc.none { it == route.second }) acc.add(route.second)

            acc.first { it == route.first }.options.add(acc.first { it == route.second }); acc
        }

    override fun part1() = mapCaves().size

    override fun part2() = options.filter { !it.fat }
        .fold(mutableSetOf<String>()) { acc, cave -> acc.addAll(mapCaves(cave).map { it.toString() }); acc }.size

    private fun mapCaves(fatCave: Cave? = null): MutableList<Path> {
        val paths = mutableListOf<Path>()
        val queue = mutableListOf(Path(options.first { it.cave == "start" }))

        while (queue.isNotEmpty()) queue.removeLast().run {
            steps.last().options
                .filter { it.fat || !steps.contains(it) || (it == fatCave && steps.filter { c -> c == it }.size < 2) }
                .forEach { val path = next(it); (if (path.steps.last().cave == "end") paths else queue).add(path) }
        }

        return paths
    }

    private class Path(cave: Cave? = null) {
        val steps = mutableListOf<Cave>().also { if (cave != null) it.add(cave) }

        fun next(cave: Cave) = Path().also { steps.forEach { s -> it.steps.add(s) }; it.steps.add(cave) }
        override fun toString() = steps.joinToString(",") { it.cave }
    }

    private data class Cave(val cave: String) {
        val options = mutableSetOf<Cave>()
        val fat = cave[0].isUpperCase()

        override fun equals(other: Any?) = other is Cave && other.cave == cave
        override fun hashCode() = cave.hashCode()
    }
}