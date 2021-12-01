package nl.sbmf21.aoc.common

abstract class ADay(private val aoc: AocBase, public val number: Int) {

    protected val input = input()

    abstract fun part1(): Number

    abstract fun part2(): Number

    private fun input() = aoc.file(this)
}
