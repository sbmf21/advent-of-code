package nl.sbmf21.aoc20.days

import nl.sbmf21.aoc.common.ADay
import java.util.regex.Matcher
import java.util.regex.Pattern.compile as regex

class Day16(input: List<String>) : ADay(input) {

    // Variables

    private val pattern = regex("(?<key>[a-z ]+): (?<ss>[\\d]+)-(?<se>[\\d]+) or (?<es>[\\d]+)-(?<ee>[\\d+]+)")
    private val tickets = ticketList("nearby tickets:")
    private val myTicket = ticketList("your ticket:", true).first()
    private val rules = input
        .map { pattern.matcher(it) }
        .filter { it.matches() }
        .map { TicketRule(it) }

    // Puzzles

    override fun part1() = tickets
        .map { t -> t.filter { f -> rules.none { it.matches(f) } } }
        .flatten()
        .sum()

    override fun part2(): Long {

        val tickets = tickets.filter { t -> t.none { f -> rules.none { it.matches(f) } } }
        val position = rules
            .associateBy({ it.key }, {
                rules
                    .associateBy { rules.indexOf(it) }
                    .mapValues { (i, _) -> tickets.filter { t -> it.matches(t[i]) } }
                    .filterValues { it.count() == tickets.count() }
                    .map { (i, _) -> i }
            })
            .entries
            .fold(mutableMapOf<Int, MutableList<String>>()) { acc, e ->
                e.value.forEach { acc.getOrPut(it) { mutableListOf() }.add(e.key) }
                acc
            }

        return rules
            .fold(mutableMapOf<String, Int>()) { acc, _ ->
                position
                    .filter { (_, l) -> l.size == 1 }
                    .forEach { (i, l) ->
                        position
                            .filter { (oi, _) -> oi != i }
                            .forEach { (pi, po) -> position[pi] = po.filter { it != l[0] }.toMutableList() }

                        acc[l[0]] = i
                    }

                acc
            }
            .filterKeys { it.startsWith("departure") }
            .map { (_, i) -> myTicket[i].toLong() }
            .reduce { acc, x -> acc * x }
    }

    // Helpers

    private fun ticketList(prefix: String, single: Boolean = false) = input
        .subList(index(prefix), if (single) index(prefix) + 1 else input.size)
        .map { t -> t.split(',').map { it.toInt() } }

    private fun index(prefix: String) = input.indexOf(prefix) + 1
}

internal class TicketRule(matcher: Matcher) {

    val key = matcher.group("key")!!
    private val range1 = matcher.group("ss").toInt()..matcher.group("se").toInt()
    private val range2 = matcher.group("es").toInt()..matcher.group("ee").toInt()

    fun matches(field: Int) = field in range1 || field in range2
}