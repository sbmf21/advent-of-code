package io.frutsel_.aoc.days

import io.frutsel_.aoc.Aoc
import io.frutsel_.aoc.common.ADay
import java.util.regex.Matcher
import java.util.regex.Pattern

class Day16(aoc: Aoc) : ADay(aoc) {

    private val rulePattern = Pattern.compile("(?<key>[a-z ]+): (?<srs>[\\d]+)-(?<sre>[\\d]+) or (?<ers>[\\d]+)-(?<ere>[\\d+]+)")
    private val rules = input.map { rulePattern.matcher(it) }.filter { it.matches() }.map { Rule(it) }
    private val tickets = input.subList(input.indexOf("nearby tickets:") + 1, input.size).map { line -> line.split(",").map { it.toInt() } }

    override fun number() = 16

    override fun part1(): Int {
        val unmatched = mutableListOf<Int>()
        tickets.forEach { ticket -> ticket.forEach { field -> if (rules.none { it.matches(field) }) unmatched.add(field) } }
        return unmatched.sum()
    }

    override fun part2() = 0
}

internal class Rule(matcher: Matcher) {

    private val srs: Int = matcher.group("srs").toInt()
    private val sre: Int = matcher.group("sre").toInt()
    private val ers: Int = matcher.group("ers").toInt()
    private val ere: Int = matcher.group("ere").toInt()

    fun matches(field: Int) = field in srs..sre || field in ers..ere
}
