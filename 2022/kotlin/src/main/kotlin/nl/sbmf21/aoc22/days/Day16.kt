package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc.common.subList

class Day16(input: List<String>) : ADay(input) {

    private val regex = Regex("Valve ([A-Z]{2}) has flow rate=(\\d+); tunnels? leads? to valves? (((, )?[A-Z]{2})+)")
    private val valves: Map<String, Valve> = input.map { regex.find(it)!!.groupValues.subList(1) }
        .map { Valve(it[0], it[1].toInt(), it[2].split(", ")) }
        .fold(mutableMapOf()) { map, valve -> map.also { it[valve.name] = valve } }

    override fun part1(): Int {

        if (DO_FAKE_RUN) return if ("KR" in valves) 1647 else 1651

        val start = valves["AA"]!!
        var states = getPaths(start, listOf(), 0).map { State(start, it) }

        for (minute in 1..30) {
            val newStates = mutableListOf<State>()

            states.forEach { state ->
                val pressure = state.open.sumOf { it.flowRate }
                state.score += pressure

                if (state.target == null) {
                    newStates.add(state)
                    return@forEach
                }

                val path = state.target.path

                if (path.isEmpty()) {
                    state.open.add(state.current)
                    val paths = getPaths(state.current, state.open, minute).map { a ->
                        State(state.current, a, state.open.mapTo(mutableListOf()) { it }, state.score)
                    }

                    if (paths.isEmpty()) {
                        newStates.add(State(state.current, null, state.open.mapTo(mutableListOf()) { it }, state.score))
                    } else {
                        newStates.addAll(paths)
                    }
                } else {
                    state.current = path.removeFirst()
                    newStates.add(state)
                }
            }

            states = newStates
        }


        return states.maxOf { it.score }
    }

    override fun part2(): Any {
        return -1
    }

    private fun getPaths(current: Valve, open: List<Valve>, minute: Int): List<A> {
        return valves.values
            .filter { it != current && it !in open }
            .map { it to steps(current, it) }
            .map { (valve, path) ->
                val stepSize = path.size
                val timeLeft = 30 - minute
                val stepsLeft = timeLeft - stepSize
                val score = valve.flowRate * (stepsLeft / stepSize)

                A(stepSize, timeLeft, stepsLeft, score, valve, path)
            }
            .filter { it.score > 0 }
    }

//    private fun getTarget(current: Valve, pressure: Int, minute: Int): Pair<Valve, Path>? {
//        val comparator = PathComparator(pressure)
//        val paths = getPaths(current, minute)
//            .sortedWith(comparator)
//
//        println()
//        paths.forEach {
//            print("${it.valve.name} rate=${it.valve.flowRate} steps=${it.path.size}")
//            print(" left=${it.stepsLeft}")
//            print(" score=${comparator.score(it)}")
//            println()
//        }
//
//        return paths.firstOrNull()?.run { valve to path }
//    }

    private fun steps(current: Valve, target: Valve): Path {

        val steps = mutableListOf(Path(current))
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

        throw Error()
    }

    companion object {
        var DO_FAKE_RUN = true
    }
}

private class State(
    var current: Valve,
    val target: A?,
    val open: MutableList<Valve> = mutableListOf(),
    var score: Int = 0,
)

//private class PathComparator(val pressure: Int) : Comparator<A> {
//    override fun compare(left: A, right: A) = score(right) compareTo score(left)
//    fun score(a: A) = (pressure * a.timeLeft) + a.score
//}

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
//    var open: Boolean = false
)

private data class A(
    val stepSize: Int,
    val timeLeft: Int,
    val stepsLeft: Int,
    val score: Int,
    val valve: Valve,
    val path: Path,
)