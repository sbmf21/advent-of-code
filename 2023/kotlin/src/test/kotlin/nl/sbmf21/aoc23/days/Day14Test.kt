package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.testing.testDay
import kotlin.test.Test

class Day14Test {

    @Test
    fun testInput() = testDay(Day14::class.java, 109_939, 101_010)

    @Test
    fun testExample() = testDay(Day14::class.java, 136, 64, true)
}