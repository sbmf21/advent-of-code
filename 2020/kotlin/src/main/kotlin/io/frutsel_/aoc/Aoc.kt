package io.frutsel_.aoc

import io.frutsel_.aoc.common.AocBase

class Aoc : AocBase("2020")

fun main(args: Array<String>) {

    val aoc = Aoc()
    aoc.init(args)

    val output = StringBuilder("Advent of Code 2020").appendLine()

    aoc.findDays().forEach { day ->
        output.appendLine()
            .appendLine("Day ${day.number()}")
            .appendLine("- Part 1")
            .appendLine("  Answer: ${day.part1()}")
            .appendLine("- Part 2")
            .appendLine("  Answer: ${day.part2()}")
    }

    print(output)
}
