package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc23.testDay
import org.junit.jupiter.api.Test

class Day5Test {

    @Test
    fun testInput() = testDay(Day5::class.java, 165_788_812L, 1_928_058L)

    @Test
    fun testExample() = testDay(Day5::class.java, 35L, 46L, true)
}