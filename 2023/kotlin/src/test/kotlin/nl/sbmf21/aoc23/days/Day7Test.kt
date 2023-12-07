package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test

class Day7Test {

    @Test
    fun testInput() = testDay(Day7::class.java, 251_121_738, 251_421_071)

    @Test
    fun testExample() = testDay(Day7::class.java, 6440, 5905, true)
}