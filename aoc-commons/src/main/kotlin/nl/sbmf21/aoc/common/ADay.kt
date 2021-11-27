package nl.sbmf21.aoc.common

abstract class ADay(private val aoc: AocBase) {

    protected val input = input()

    abstract fun number(): Int

    abstract fun part1(): Number

    abstract fun part2(): Number

    private fun input() = aoc.file(this)
}
