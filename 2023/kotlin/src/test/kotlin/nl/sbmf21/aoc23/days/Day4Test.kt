package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc23.testDay
import org.junit.jupiter.api.Test

class Day4Test {

    @Test
    fun testInput() = testDay(Day4::class.java, 24_542, 8_736_438)

    @Test
    fun testExample() = testDay(Day4::class.java, 13, 30, true)
}