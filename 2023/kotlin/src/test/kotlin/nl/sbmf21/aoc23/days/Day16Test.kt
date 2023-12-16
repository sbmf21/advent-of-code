package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test

class Day16Test {

    @Test
    fun testInput() = testDay(Day16::class.java, 8551, 8754)

    @Test
    fun testExample() = testDay(Day16::class.java, 46, 51, true)
}