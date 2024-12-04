package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.testing.testDay
import kotlin.test.Test

class Day7Test {

    @Test
    fun testInput() = testDay(Day7::class.java, 347_449, 98_039_527)

    @Test
    fun testExample() = testDay(Day7::class.java, 37, 168, true)
}