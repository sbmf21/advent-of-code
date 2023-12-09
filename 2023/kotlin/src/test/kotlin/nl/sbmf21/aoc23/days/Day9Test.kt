package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test

class Day9Test {

    @Test
    fun testInput() = testDay(Day9::class.java, 1_581_679_977L, 889L)

    @Test
    fun testExample() = testDay(Day9::class.java, 114L, 2L, true)
}