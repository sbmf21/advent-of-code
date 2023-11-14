package nl.sbmf21.aoc20.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.iterated
import nl.sbmf21.aoc.common.mapToInts

class Day19 : Day() {

    private val pattern = Regex("(\\d+):\\s(.+)")
    private val simplePattern = Regex("\".+\"")
    private val messages = input.filter { !it.matches(pattern) }.filter { it.isNotBlank() }

    override fun part1() = buildRules().run { messages.filter { matches(this, it) }.size }

    override fun part2(): Int {
        return -1

        // This does not work :rolling_eyes:
        return buildRules(input
            .map {
                when (it) {
                    "8: 42" -> "8: 42 | 42 8"
                    "11: 42 31" -> "11: 42 31 | 42 11 31"
                    else -> it
                }
            })
            .run { messages.filter { matches(this, it) }.size }
    }

    private fun matches(rules: Map<Int, BaseRule>, message: String, rule: Int = 0) =
        rules[rule]!!.matches(message, 0) == message.length

    private fun buildRules(input: List<String> = this.input): Map<Int, BaseRule> {
        val rules = mutableMapOf<Int, BaseRule>()
        val matched = input.mapNotNull { pattern.matchEntire(it) }
            .map { it.groups }
            .toMutableList()

        matched.removeAll(matched
            .filter { simplePattern.matches(it[2]!!.value) }
            .onEach { rules[it[1]!!.value.toInt()] = MessageRule(it[2]!!.value[1]) })

        while (matched.isNotEmpty()) matched.iterated { iter, it ->
            val ids = it[2]!!.value
                .split("|")
                .map { it.trim().split(" ").mapToInts() }

            if (ids.flatten().distinct().all { rules.containsKey(it) }) {
                rules[it[1]!!.value.toInt()] = RuleRule(ids.map { it.map { id -> rules[id]!! } })
                iter.remove()
            }
        }

        return rules.toMap()
    }

    private interface BaseRule {
        fun matches(message: String, index: Int): Int?
    }

    private data class MessageRule(val match: Char) : BaseRule {
        override fun matches(message: String, index: Int) = if (message[index] == match) index + 1 else null
    }

    private data class RuleRule(val match: List<List<BaseRule>>) : BaseRule {
        override fun matches(message: String, index: Int) = match.firstNotNullOfOrNull {
            it.fold(index) { acc: Int?, r -> if (acc != null) r.matches(message, acc) else null }
        }
    }
}