package io.frutsel_.aoc

import java.util.stream.Stream

abstract class Day(private val aoc: Aoc) {

    protected fun input(): Stream<String> = aoc.file(this)

    abstract fun number(): Int

    abstract fun part1(): Number

    abstract fun part2(): Number
}