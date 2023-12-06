package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test

class Day14Test {

    @Test
    fun testInput() = testDay(Day14::class.java, 1406, 20870)

    @Test
    fun testExample() = testDay(Day14::class.java, 24, 93, true)
}