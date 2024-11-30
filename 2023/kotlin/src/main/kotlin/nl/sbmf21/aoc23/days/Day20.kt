package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc23.days.Day20.Pulse.HIGH
import nl.sbmf21.aoc23.days.Day20.Pulse.LOW

class Day20 : Day() {

    private var modules = read()

    private fun read() = input.associate { line ->
        val (name, outputs) = line.split(" -> ", limit = 2).let { it[0] to it[1].split(", ") }
        val module = when {
            name.startsWith('%') -> FlipFlop(name.substring(1), outputs)
            name.startsWith('&') -> Conjunction(name.substring(1), outputs)
            name == "broadcaster" -> Broadcast(name, outputs)
            else -> throw Error("Unknown handle: $name")
        }
        module.name to module
    }

    override fun part1(): Any {

        var lows = 1000
        var highs = 0

        repeat(1000) {
            val next = mutableListOf<Pair<String, Map<String, Pulse>>>()
            val starts = modules["broadcaster"]!!.send("button", LOW)
            next.add(starts)

            while (next.isNotEmpty()) {
                val current = next.removeFirst()

                lows += current.second.count { it.value == LOW }
                highs += current.second.count { it.value == HIGH }

                current.second.forEach { (module, pulse) ->
                    next.add(module.send(current.first, pulse))
                }
            }
        }

        return lows * highs
    }

    override fun part2(): Any {

        return nl.sbmf21.aoc.common.TODO

//        modules = read()
//
//        var i = 0
//
//        val output = modules.flatMap { it.value.outputs }.distinct().first { it !in modules }.also(::println)
//
//        main@ while (true) {
//            i++
//
//            if (i % 10_000 == 0) println(i)
//
//            val next = mutableListOf<Pair<String, Map<String, Pulse>>>()
//            val starts = modules["broadcaster"]!!.send("button", LOW)
//            next.add(starts)
//
//            while (next.isNotEmpty()) {
//                val current = next.removeFirst()
//
//                if (output in current.second && current.second[output] == LOW) break@main
//
//                current.second.forEach { (module, pulse) ->
//                    next.add(module.send(current.first, pulse))
//                }
//            }
//        }
//
//        return i
    }

    private fun String.send(from: String, pulse: Pulse): Pair<String, Map<String, Pulse>> {
        return modules[this]?.send(from, pulse) ?: (from to mapOf())
    }

    private sealed interface Module {

        val name: String
        val outputs: List<String>

        fun send(input: String, pulse: Pulse): Pair<String, Map<String, Pulse>>
    }

    private inner class Broadcast(override val name: String, override val outputs: List<String>) : Module {
        override fun send(input: String, pulse: Pulse) = name to outputs.associateWith { pulse }
    }

    private inner class Conjunction(override val name: String, override val outputs: List<String>) : Module {

        private val history by lazy {
            modules.filterValues { it.outputs.contains(name) }.mapValues { LOW }.toMutableMap()
        }

        override fun send(input: String, pulse: Pulse): Pair<String, Map<String, Pulse>> {
            history[input] = pulse
            return name to if (history.all { (_, state) -> state == HIGH }) {
                outputs.associateWith { LOW }
            } else {
                outputs.associateWith { HIGH }
            }
        }
    }

    private inner class FlipFlop(override val name: String, override val outputs: List<String>) : Module {

        private var state = LOW

        override fun send(input: String, pulse: Pulse): Pair<String, Map<String, Pulse>> {
            if (pulse != LOW) return name to mapOf()
            state = !state
            return name to outputs.associateWith { state }
        }
    }

    private enum class Pulse {
        HIGH,
        LOW;

        operator fun not() = if (this == HIGH) LOW else HIGH
    }
}