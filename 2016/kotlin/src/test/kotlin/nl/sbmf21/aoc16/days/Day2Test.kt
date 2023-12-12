package nl.sbmf21.aoc16.days

import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test

class Day2Test {

    @Test
    fun testInput() = testDay(Day2::class.java, "82958", "B3DB8")

    @Test
    fun testExample() = testDay(Day2::class.java, "1985", "5DB3", true)
}