package nl.sbmf21.aoc20.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc20.Aoc
import java.util.regex.Matcher
import java.util.regex.Pattern

class Day16(aoc: Aoc) : ADay(aoc) {

    private val pattern = Pattern.compile("(?<key>[a-z ]+): (?<ss>[\\d]+)-(?<se>[\\d]+) or (?<es>[\\d]+)-(?<ee>[\\d+]+)")
    private val rules = input
        .map { pattern.matcher(it) }
        .filter { it.matches() }
        .map { Rule(it) }
    private val tickets = input
        .subList(input.indexOf("nearby tickets:") + 1, input.size)
        .map { line -> line.split(",").map { it.toInt() } }

    override fun number() = 16

    override fun part1(): Int {
        val unmatched = mutableListOf<Int>()
        tickets.forEach { ticket -> ticket.forEach { field -> if (rules.none { it.matches(field) }) unmatched.add(field) } }
        return unmatched.sum()
    }

    override fun part2(): Int {

        tickets
            .filter { it.forEach { f -> if (rules.none { r -> r.matches(f) }) return@filter false }; true }
            .forEachIndexed { index, number ->
                // per nummer bekijken welke rules hij matched, opslaan op index
            }

        return 0
    }
}

internal class Rule(matcher: Matcher) {

    private val key = matcher.group("key")
    private val ss = matcher.group("ss").toInt()
    private val se = matcher.group("se").toInt()
    private val es = matcher.group("es").toInt()
    private val ee = matcher.group("ee").toInt()

    fun matches(field: Int) = field in ss..se || field in es..ee
}
