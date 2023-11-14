package nl.sbmf21.aoc.common

abstract class ADay {

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
}