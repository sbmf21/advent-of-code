package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test

class Day15Test {

    @Test
    fun testInput() = testDay(Day15::class.java, 514_639, 279_470)

    @Test
    fun testExample() = testDay(Day15::class.java, 1320, 145, true)
}