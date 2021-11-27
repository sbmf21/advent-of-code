package nl.sbmf21.aoc20.days

import nl.sbmf21.aoc20.Aoc
import nl.sbmf21.aoc.common.ADay
import java.util.regex.Matcher
import java.util.regex.Pattern

class Day2(aoc: Aoc) : ADay(aoc) {

    private val pattern = Pattern.compile("(?<min>\\d+)-(?<max>\\d+)\\s(?<char>[a-z]):\\s(?<password>[a-z]+)")
    private val rules = input.map { pattern.matcher(it) }.filter { it.matches() }

    override fun number() = 2

    override fun part1() = rules.count { line ->
        val charCount = password(line).filter { it == char(line) }.length

        (charCount >= min(line)).and(charCount <= max(line))
    }

    override fun part2() = rules.count {
        val password = password(it)
        val char = char(it)

        (password[min(it) - 1] == char).xor(password[max(it) - 1] == char)
    }

    private fun password(matcher: Matcher): String = matcher.group("password")
    private fun char(matcher: Matcher): Char = matcher.group("char")[0]
    private fun min(matcher: Matcher): Int = matcher.group("min").toInt()
    private fun max(matcher: Matcher): Int = matcher.group("max").toInt()
}
