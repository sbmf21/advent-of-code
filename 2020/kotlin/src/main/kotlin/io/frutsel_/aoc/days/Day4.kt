package io.frutsel_.aoc.days

import io.frutsel_.aoc.ADay
import io.frutsel_.aoc.Aoc
import java.util.regex.Pattern

@Suppress("unused")
class Day4(aoc: Aoc) : ADay(aoc) {
    private val passports = parsePassports()

    override fun number(): Int = 4

    override fun part1(): Number = passports.filter { hasRequiredFields(it) }.size

    override fun part2(): Number = passports.filter { hasValidFields(it) }.size


    private fun parsePassports(): List<String> {

        val empty = input
            .mapIndexedTo(mutableListOf()) { index: Int, _: String -> index }
            .filter { input[it].isBlank() }
            .map { it }
            .toMutableList()

        empty.add(input.size)

        var lastIndex = 0
        return empty.map {
            val list = input.subList(lastIndex, it)
                .filter { line -> line.isBlank().not() }
                .reduce { acc, s -> "$acc $s" }

            lastIndex = it

            list
        }
    }

    private fun hasRequiredFields(passport: String): Boolean {
        Rules.values()
            .map { it.name.toLowerCase() }
            .map { Pattern.compile("$it:.+").matcher(passport) }
            .forEach {
                if (it.find().not()) {
                    return false
                }
            }

        return true
    }

    private fun hasValidFields(passport: String): Boolean {
        Rules.values().forEach {
            if (it.isValid(passport).not()) {
                return false
            }
        }

        return true
    }
}

internal enum class Rules(pattern: String, private val validate: (String) -> Boolean = { _ -> true }) {
    BYR("\\d{4}", ::validateByr),
    IYR("\\d{4}", ::validateIyr),
    EYR("\\d{4}", ::validateEyr),
    HGT("\\d{3}cm|\\d{2}in", ::validateHgr),
    HCL("#[\\da-f]{6}"),
    ECL("amb|blu|brn|gry|grn|hzl|oth"),
    PID("\\d{9}");

    private val completePattern = Pattern.compile("${this.name.toLowerCase()}:(?<value>$pattern) ")

    fun isValid(passport: String): Boolean {
        val matcher = matcher("$passport ") // extra space on the end

        if (matcher.find().not()) {
            return false
        }

        return validate(matcher.group("value"))
    }

    private fun matcher(passport: String) = completePattern.matcher(passport)
}

internal fun findNumber(value: String): Int = Regex("\\d*").find(value)?.value?.toInt()!!
internal fun validateByr(value: String): Boolean = value.toInt() in 1920..2002
internal fun validateIyr(value: String): Boolean = value.toInt() in 2010..2020
internal fun validateEyr(value: String): Boolean = value.toInt() in 2020..2030
internal fun validateHgr(value: String): Boolean = when (Regex("cm|in").find(value)?.value) {
    "cm" -> findNumber(value) in 150..193
    else -> findNumber(value) in 59..76
}
