package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.testing.testDay
import kotlin.test.Test

class Day12Test {

    @Test
    fun testInput() = testDay(Day12::class.java, 6958L, 6_555_315_065_024L)

    @Test
    fun testExample() = testDay(Day12::class.java, 21L, 525_152L, true)
}