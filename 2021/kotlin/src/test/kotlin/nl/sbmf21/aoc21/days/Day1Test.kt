package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test

class Day1Test {

    @Test
    fun testInput() = testDay(Day1::class.java, 1759, 1805)

    @Test
    fun testExample() = testDay(Day1::class.java, 7, 5, true)
}