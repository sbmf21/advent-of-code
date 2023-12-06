package nl.sbmf21.aoc16.days

import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test

class Day5Test {

    @Test
    fun testInput() = testDay(Day5::class.java, "f77a0e6e", "999828ec")

    @Test
    fun testExample() = testDay(Day5::class.java, "18f47a30", "05ace8e3", true)
}