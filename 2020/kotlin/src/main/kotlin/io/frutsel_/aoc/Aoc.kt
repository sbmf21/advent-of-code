package io.frutsel_.aoc

import io.frutsel_.aoc.common.AocBase

class Aoc : AocBase("2020")

fun main(args: Array<String>) {
    val aoc = Aoc()

    aoc.init(args)
    aoc.runDays()
    aoc.report()
}
