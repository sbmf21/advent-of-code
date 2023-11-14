package nl.sbmf21.aoc20.days

import nl.sbmf21.aoc.common.Day
import java.util.regex.Pattern

class Day4 : Day() {

    private val passports = parsePassports()

    override fun part1() = passports.filter { hasRequiredFields(it) }.size

    override fun part2() = passports.filter { hasValidFields(it) }.size

    private fun parsePassports(): List<String> {
        val emptyLines = input
            .mapIndexedTo(mutableListOf()) { index: Int, _: String -> index }
            .asSequence()
            .filter { input[it].isBlank() }
            .map { it }
            .toMutableList()
            .plus(input.size)
        return emptyLines.mapIndexed { index: Int, it: Int ->
            input.subList(if (index == 0) 0 else emptyLines[index - 1], it)
                .filter { line -> line.isBlank().not() }
                .reduce { acc, s -> "$acc $s" }
        }
    }

    private fun hasRequiredFields(passport: String) = Rules.entries
        .map { it.name.lowercase() }
        .map { Pattern.compile("$it:.+").matcher(passport) }
        .filter { it.find() }.size == Rules.entries.size

    private fun hasValidFields(passport: String) =
        Rules.entries.filter { it.isValid(passport) }.size == Rules.entries.size

    private companion object {
        fun findNumber(value: String) = Regex("\\d*").find(value)?.value?.toInt()!!
    }

    private enum class Rules(private var pattern: String, private val validate: (String) -> Boolean = { _ -> true }) {
        BYR("\\d{4}", { it.toInt() in 1920..2002 }),
        IYR("\\d{4}", { it.toInt() in 2010..2020 }),
        EYR("\\d{4}", { it.toInt() in 2020..2030 }),
        HGT("\\d{3}cm|\\d{2}in", {
            when (Regex("cm|in").find(it)?.value) {
                "cm" -> findNumber(it) in 150..193
                else -> findNumber(it) in 59..76
            }
        }),
        HCL("#[\\da-f]{6}"),
        ECL("amb|blu|brn|gry|grn|hzl|oth"),
        PID("\\d{9}");

        fun isValid(passport: String): Boolean {
            val matcher = Pattern.compile("${this.name.lowercase()}:(?<value>$pattern) ").matcher("$passport ")
            return if (matcher.find()) validate(matcher.group("value")) else false
        }
    }
}