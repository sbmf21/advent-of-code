package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay

class Day16(input: List<String>) : ADay(input) {

    private companion object {
        const val ALONE_MINUTES = 30
        const val HELP_MINUTES = 26
    }

    private val regex = Regex("Valve ([A-Z]{2}) has flow rate=(\\d+); tunnels? leads? to valves? (((, )?[A-Z]{2})+)")
    private val valves: List<Valve> = input
        .map { regex.find(it)!!.groupValues }
        .map { Valve(it[1], it[2].toInt(), it[3].split(", ").toSet()) }
    private val distances = valves.associateWith { valve ->
        buildMap<Valve, Int> {
            put(valve, 0)
            val checks = mutableListOf(valve)
            while (checks.isNotEmpty()) checks.removeFirst().also { current ->
                current.tunnels.forEach {
                    valves.first { v -> v.name == it }.also { other ->
                        (getOrDefault(current, Int.MAX_VALUE) + 1).also {
                            if (it < getOrDefault(other, Int.MAX_VALUE)) {
                                put(other, it)
                                checks.add(other)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun part1() = checkValves(ALONE_MINUTES)

    override fun part2() = checkValves(HELP_MINUTES, finalize = { value, valves ->
        maxOf(value, checkValves(HELP_MINUTES, valves = valves))
    })

    private fun checkValves(
        minute: Int,
        valve: Valve = this.valves.first { it.name == "AA" },
        valves: Set<Valve> = this.valves.filter { it.flowRate > 0 }.toSet(),
        seen: MutableMap<State, Int> = mutableMapOf(),
        finalize: (Int, Set<Valve>) -> Int = { value, _ -> value },
    ): Int = minute * valve.flowRate + seen.getOrPut(State(valve, minute, valves)) {
        finalize(
            valves
                .filter { distances[valve]!![it]!! < minute }
                .takeIf { it.isNotEmpty() }
                ?.maxOf { checkValves(minute - 1 - distances[valve]!![it]!!, it, valves - it, seen, finalize) }
                ?: 0,
            valves,
        )
    }

    private data class State(val current: Valve, val minute: Int, val opened: Set<Valve>)

    private data class Valve(val name: String, val flowRate: Int, val tunnels: Set<String>)
}