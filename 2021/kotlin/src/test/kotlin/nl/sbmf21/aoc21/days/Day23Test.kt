package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.testing.testDay
import kotlin.test.Test

class Day23Test {

    @Test
    fun testInput() = testDay(Day23::class.java, 13_520, 48_708)

    @Test
    fun testExample() = testDay(Day23::class.java, 12_521, 44_169, true)
}