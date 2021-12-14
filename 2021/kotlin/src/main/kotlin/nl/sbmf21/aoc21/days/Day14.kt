package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay
import kotlin.math.ceil
import kotlin.streams.toList

class Day14(input: List<String>) : ADay(input) {

    private val template = input[0]
    private val rules = input.subList(2, input.size).map { it.split(" -> ") }

    override fun part1() = (1..10)
        .fold(template) { current, _ ->
            (0 until current.length - 1).fold("${current[0]}") { str, i ->
                val pair = current.substring(i, i + 2)
                str + rules.first { it[0] == pair }[1] + pair[1]
            }
        }
        .run { chars().distinct().toList().map { c -> chars().toList().count { it == c } } }
        .run { maxOf { it } - minOf { it } }

    // I had a little help with this one, I am not good at coming up with these kinds of logic.
    override fun part2() = (0 until template.length - 1)
        .fold(mutableMapOf<String, Long>()) { acc, i -> acc[template.substring(i, i + 2)] = 1; acc }
        .run {
            (1..40).fold(this) { acc, _ ->
                acc.entries.fold(mutableMapOf()) { pairs, (pair, count) ->
                    val new = rules.first { it[0] == pair }[1]; val np1 = pair[0] + new; val np2 = new + pair[1]
                    pairs[np1] = count + (pairs[np1] ?: 0); pairs[np2] = count + (pairs[np2] ?: 0); pairs
                }
            }
        }
        .entries
        .fold(mutableMapOf<Char, Long>()) { acc, e -> e.key.forEach { c -> acc[c] = (acc[c] ?: 0) + e.value }; acc }
        .map { ceil(it.value / 2.0).toLong() }
        .run { maxOf { it } - minOf { it } }
}
