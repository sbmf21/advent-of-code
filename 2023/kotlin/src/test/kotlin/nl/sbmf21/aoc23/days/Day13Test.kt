package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test

class Day13Test {

    @Test
    fun testInput() = testDay(Day13::class.java, 34918, 33054)

    @Test
    fun testExample() = testDay(Day13::class.java, 405, 400, true)
}