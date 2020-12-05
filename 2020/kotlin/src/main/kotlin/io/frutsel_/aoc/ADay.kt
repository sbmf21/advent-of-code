package io.frutsel_.aoc

abstract class ADay(private val aoc: Aoc) {

    protected val input = input()

    abstract fun number(): Int

    abstract fun part1(): Number

    abstract fun part2(): Number

    private fun input(): List<String> = aoc.file(this)
}
