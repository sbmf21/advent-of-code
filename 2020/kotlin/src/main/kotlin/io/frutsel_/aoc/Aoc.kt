package io.frutsel_.aoc

import org.reflections.Reflections
import java.util.stream.Stream

fun main() {
    val output = StringBuilder("Advent of Code 2020").appendLine()

    findDays().forEach { day ->
        output.appendLine()
            .appendLine("Day ${day.number()}")
            .appendLine("- Part 1")
            .appendLine("  Answer: ${day.part1()}")
            .appendLine("- Part 2")
            .appendLine("  Answer: ${day.part2()}")
    }

    println(output)
}

private fun findDays() = Reflections("${Day::class.java.packageName}.days")
    .getSubTypesOf(Day::class.java)
    .map { it.getDeclaredConstructor().newInstance() }
    .sortedBy { it.number() }

fun file(day: Day): Stream<String> = day::class.java
    .getResourceAsStream("/day${day.number()}.txt")
    .bufferedReader()
    .lines()

internal fun <T> Array<T>.mapToInt(): List<Int> = this.map { it.toString().toInt() }
