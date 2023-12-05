package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc23.testDay
import org.junit.jupiter.api.Test

class Day5Test {

    // 58853829 too high
    // 220731594

    @Test
    fun testInput() = testDay(Day5::class.java, 165788812L, -1L)

    // example part to = 46

    @Test
    fun testExample() = testDay(Day5::class.java, 35L, -1L, true)
}