package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc.common.subList

class Day16(input: List<String>) : ADay(input) {

    private val regex = Regex("Valve ([A-Z]{2}) has flow rate=(\\d+); tunnels? leads? to valves? (((, )?[A-Z]{2})+)")

    private val valves: Map<String, Valve> = input.map { regex.find(it)!!.groupValues.subList(1) }
        .map { Valve(it[0], it[1].toInt(), it[2].split(", ")) }
        .fold(mutableMapOf()) { map, valve -> map.also { it[valve.name] = valve } }

    override fun part1(): Any {
        var current = valves["AA"]!!
        var target = getTarget(current, 0, 0)
        var score = 0

        for (minute in 1..30) {
            println("== Minute $minute ==")
            val pressure = valves.values.filter { it.open }.sumOf { it.flowRate }
            score += pressure

            if (score > 0) {
                val open = valves.values.filter { it.open }
                val valves = if (open.size == 1) "Valve" else "Valves"
                val openString = open.joinToString(if (open.size == 2) " and " else ", ") { it.name }
                val are = if (open.size == 1) "is" else "are"
                println("$valves $openString $are open, releasing $pressure pressure.")
            } else {
                println("No valves are open.")
            }

            if (target == null) {
                println()
                continue
            }
            val (_, path) = target

            if (path.isEmpty()) {
                println("You open valve ${current.name}.")
                current.open = true
                target = getTarget(current, pressure, minute)
            } else {
                current = path.removeFirst()
                println("You move to valve ${current.name}.")
            }
            println()
        }
        return score
    }

    override fun part2(): Any {
        return -1
    }

    private fun getTarget(current: Valve, pressure: Int, minute: Int): Pair<Valve, Path>? {
        val comparator = PathComparator(pressure)

        val paths = valves.values
            .asSequence()
            .filter { it != current && !it.open }
            .map { it to steps(current, it) }
            .map { (valve, path) ->
                val stepSize = path.size
                val timeLeft = 30 - minute
                val stepsLeft = timeLeft - stepSize
                val score = valve.flowRate * (stepsLeft / stepSize)

                A(stepSize, timeLeft, stepsLeft, score, valve, path)
            }
            .filter { it.score > 0 }
            .sortedWith(comparator)

        println()
        paths.forEach {
            print("${it.valve.name} rate=${it.valve.flowRate} steps=${it.path.size}")
            print(" left=${it.stepsLeft}")
            print(" score=${comparator.score(it)}")
            println()
        }

        return paths.firstOrNull()?.run { valve to path }
    }

    private data class A(
        val stepSize: Int,
        val timeLeft: Int,
        val stepsLeft: Int,
        val score: Int,
        val valve: Valve,
        val path: Path,
    )

    private class PathComparator(val pressure: Int) : Comparator<A> {
        override fun compare(left: A, right: A) = score(right) compareTo score(left)
        fun score(a: A) = (pressure * a.timeLeft) + a.score
    }

    private fun steps(current: Valve, target: Valve): Path {

        val states = mutableListOf(State(Path(current)))
//        val seen = mutableSetOf<Valve>()

        val finalStates = mutableListOf<State>()

        while (states.isNotEmpty()) {
            val state = states.removeLast()

            if (state.path.valve == target) {
                finalStates.add(state)
                continue
            }
            if (state.path.valve in state.seen) continue
            state.seen.add(state.path.valve)

            val options = state.path.valve.tunnels
                .map { valves[it]!! }
                .filter { it !in state.path }
                .map { State(state.path.next(it)) }

            states.addAll(options)
        }

        return finalStates.minBy { it.path.size }.path
    }
}

private class State(
    val path: Path,
    val seen: MutableList<Valve> = mutableListOf(),
)

private class Path(
    val valve: Valve,
    steps: MutableList<Valve> = mutableListOf(),
) : MutableList<Valve> by steps {
    fun next(next: Valve): Path {
        val steps = this.mapTo(mutableListOf()) { it }.also { it.add(next) }
        return Path(next, steps)
    }
}

private data class Valve(
    val name: String,
    val flowRate: Int,
    val tunnels: List<String>,
    var open: Boolean = false
)
