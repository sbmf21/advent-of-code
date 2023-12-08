package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.subList
import nl.sbmf21.aoc23.days.Day8.Instruction.LEFT
import nl.sbmf21.aoc23.days.Day8.Instruction.RIGHT
import nl.sbmf21.math.lcm

private typealias Day8Node = Pair<String, String>

class Day8 : Day() {

    private val instructions = input[0].map {
        when (it) {
            'L' -> LEFT
            'R' -> RIGHT
            else -> throw Error("Invalid instruction: '${it}'")
        }
    }

    private val nodes = input.subList(2).associate {
        val parts = it.split(" = ")
        val lr = parts[1].substring(1, parts[1].length - 1).split(", ", limit = 2)
        parts[0] to (lr[0] to lr[1])
    }

    override fun part1() = solve("AAA") { it != "ZZZ" }

    override fun part2() = nodes.keys
        .filter { it.endsWith('A') }
        .lcm { start -> solve(start) { !it.endsWith('Z') } }

    private fun solve(start: String, checkNext: (String) -> Boolean): Long {
        var step = 0L
        var current = start

        do {
            current = instructions[(step++ % instructions.size).toInt()](nodes[current]!!)
        } while (checkNext(current))

        //shut the hell up intellij
        @Suppress("KotlinConstantConditions")
        return step
    }

    private enum class Instruction(private val stepper: (Day8Node) -> String) {
        LEFT(Day8Node::first),
        RIGHT(Day8Node::second);

        operator fun invoke(node: Day8Node) = stepper(node)
    }
}