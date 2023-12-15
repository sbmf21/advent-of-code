package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day

class Day15 : Day() {

    private val instructions = input[0].split(',')

    override fun part1() = instructions.sumOf(::hash)

    override fun part2() = List(256) { mutableMapOf<String, Int>() }
        .also { boxes ->
            instructions.forEach { instruction ->
                when {
                    instruction.contains("-") -> {
                        val label = instruction.substring(0, instruction.length - 1)
                        boxes[hash(label)] -= label
                    }

                    instruction.contains("=") -> {
                        val (label, length) = instruction.split("=").run { Pair(this[0], this[1].toInt()) }
                        boxes[hash(label)][label] = length
                    }
                }
            }
        }
        .mapIndexed { idx, box ->
            box.toList().mapIndexed { slot, (_, length) ->
                (idx + 1) * (slot + 1) * length
            }.sum()
        }.sum()

    private fun hash(test: String) = test.fold(0) { current, c -> (current + c.code) * 17 % 256 }
}