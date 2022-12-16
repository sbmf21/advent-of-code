package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc.common.subList

class Day16(input: List<String>) : ADay(input) {

    private val regex = Regex("Valve ([A-Z]{2}) has flow rate=(\\d+); tunnels? leads? to valves? (((, )?[A-Z]{2})+)")

    private val valves: Map<String, Valve> = input.map { regex.find(it)!!.groupValues.subList(1) }
        .map { Valve(it[0], it[1].toInt(), it[2].split(", ")) }
        .fold(mutableMapOf()) { map, valve -> map.also { it[valve.name] = valve } }

    override fun part1(): Any {
//        var current =
//        var flow = 0

//        var paths = listOf(Path(valves["AA"]!!))


        var current = valves["AA"]!!
        var target: Pair<Valve, Path>? = null
        var score = 0

        for (minute in 1..30) {
            println("== Minute $minute ==")
            if (valves.values.any { it.open }) {
                val pressure = valves.values.filter { it.open }.sumOf { it.flowRate }
                println("Valves ${
                    valves.values.filter { it.open }.joinToString(", ") { it.name }
                } are open, releasing $pressure pressure.")

                score += pressure
            } else println("No valves are open.")

            if (target == null || target.second.steps.isEmpty()) {
                val paths = valves
                    .filter { (_, valve) -> !valve.open }
                    .values

                    .map { it to steps(current, it) }
                    .filter { it.second != null }
                    .sortedByDescending { (it.first.flowRate - it.second!!.steps.size) * (30 - minute) }

                // DD - 20 * 28 = 560
                // BB - 13 * 25 = 325
                // JJ - 21 * 21 = 461
                // HH - 22 * 13 = 286
                // EE -  3 *  9 = 27
                // CC -  2 *  6 = 12

                target = paths.first() as Pair<Valve, Path>
            }

            if (target.first == current) {
                println("You open valve ${current.name}.")
                current.open = true
            } else {
                val step = target.second.steps.removeFirst()
                current = step
                println("You move to valve ${current.name}.")
            }
            println()
        }
        return score
    }

    override fun part2(): Any {
        return -1
    }

    private fun steps(current: Valve, target: Valve): Path? {

        val steps = ArrayDeque<Path>()
        steps.add(Path(current))

        val seen = mutableListOf<Valve>()

        while (steps.isNotEmpty()) {
            val step = steps.removeFirst()

            if (step.valve == target) return step

            if (step.valve in seen) continue
            seen.add(step.valve)

            steps.addAll(step.valve.tunnels
                .map { valves[it]!! }
                .filter { it !in seen }
                .map { step.next(it) })
        }

        return null
    }
}

private class Path(
    val valve: Valve,
    var steps: MutableList<Valve> = mutableListOf(),
) {
    fun next(next: Valve): Path {
        val steps = this.steps.map { it }.toMutableList()
        steps.add(next)
        return Path(next, steps)
    }
}

private class Valve(
    val name: String,
    val flowRate: Int,
    val tunnels: List<String>,
) {
    var open = false
}
