package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc23.testDay
import org.junit.jupiter.api.Test

class Day6Test {

    @Test
    fun testInput() = testDay(Day6::class.java, 131_376, 34_123_437)

    @Test
    fun testExample() = testDay(Day6::class.java, 288, 71_503, true)
}