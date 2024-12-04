package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.testing.testDay
import kotlin.test.Test

class Day2Test {

    @Test
    fun testInput() = testDay(Day2::class.java, 15632, 14416)

    @Test
    fun testExample() = testDay(Day2::class.java, 15, 12, true)
}