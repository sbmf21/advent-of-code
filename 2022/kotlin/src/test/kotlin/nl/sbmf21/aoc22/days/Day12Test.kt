package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test

class Day12Test {

    @Test
    fun testInput() = testDay(Day12::class.java, 517, 512)

    @Test
    fun testExample() = testDay(Day12::class.java, 31, 29, true)
}