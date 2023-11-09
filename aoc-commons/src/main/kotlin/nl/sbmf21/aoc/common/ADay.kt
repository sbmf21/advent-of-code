package nl.sbmf21.aoc.common

import kotlin.properties.Delegates

abstract class ADay(val input: List<String>) {

    var number by Delegates.notNull<Int>()
        internal set

    abstract fun part1(): Any

    abstract fun part2(): Any
}