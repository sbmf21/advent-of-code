package nl.sbmf21.aoc.common

abstract class Day : Comparable<Day> {

    val number: Int
    val input: List<String>

    init {
        Cache[this::class.java].let { (number, input) ->
            this.number = number
            this.input = input
        }
    }

    abstract fun part1(): Any

    abstract fun part2(): Any

    override fun compareTo(other: Day) = number.compareTo(other.number)
}