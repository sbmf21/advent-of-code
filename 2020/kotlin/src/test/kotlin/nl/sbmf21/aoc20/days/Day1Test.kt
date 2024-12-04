package nl.sbmf21.aoc20.days

import nl.sbmf21.aoc.testing.testDay
import kotlin.test.Test

class Day1Test {

    @Test
    fun testInput() = testDay(Day1::class.java, 1_019_904, 176_647_680)

    @Test
    fun testExample() = testDay(Day1::class.java, 514_579, 241_861_950, true)
}