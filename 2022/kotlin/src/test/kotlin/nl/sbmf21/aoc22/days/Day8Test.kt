package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test

class Day8Test {

    @Test
    fun testInput() = testDay(Day8::class.java, 1814, 330_786)

    @Test
    fun testExample() = testDay(Day8::class.java, 21, 8, true)
}