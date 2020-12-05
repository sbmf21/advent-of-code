package io.frutsel_.aoc

import java.util.function.Consumer
import java.util.stream.Stream

fun main() {
    val days = setOf(Day1())
    val builder = StringBuilder("Advent of Code 2020").appendLine()

    days.forEach(Consumer { day ->
        builder.appendLine()
            .appendLine("Day ${day.number()}")
            .appendLine("- Part 1")
            .appendLine("  Answer: ${day.part1()}")
            .appendLine("- Part 2")
            .appendLine("  Answer: ${day.part2()}")
    })

    println(builder)
}

fun file(day: Day): Stream<String> = day::class.java
    .getResourceAsStream("/day${day.number()}.txt")
    .bufferedReader()
    .lines()

internal fun <T> Array<T>.mapToInt(): List<Int> = this.map { it.toString().toInt() }
