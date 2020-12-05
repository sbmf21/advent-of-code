package io.frutsel_.aoc

import org.reflections.Reflections

class Aoc {
    fun findDays() = Reflections("${ADay::class.java.packageName}.days")
        .getSubTypesOf(ADay::class.java)
        .map { it.getDeclaredConstructor(Aoc::class.java).newInstance(this) }
        .sortedBy { it.number() }

    fun file(day: ADay): List<String> = day.javaClass
        .getResourceAsStream("/input/day${day.number()}.txt")
        .bufferedReader()
        .lines()
        .toArray()
        .map { it.toString() }
}

fun main() {
    val output = StringBuilder("Advent of Code 2020").appendLine()
    val aoc = Aoc()

    aoc.findDays().forEach { day ->
        output.appendLine()
            .appendLine("Day ${day.number()}")
            .appendLine("- Part 1")
            .appendLine("  Answer: ${day.part1()}")
            .appendLine("- Part 2")
            .appendLine("  Answer: ${day.part2()}")
    }

    println(output)
}
