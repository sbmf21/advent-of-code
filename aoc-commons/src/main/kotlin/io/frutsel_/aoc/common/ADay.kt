package io.frutsel_.aoc.common

abstract class ADay(private val aoc: AocBase) {

    protected val input = input()

    abstract fun number(): Int

    abstract fun part1(): Int

    abstract fun part2(): Int

    private fun input() = aoc.file(this)
}
