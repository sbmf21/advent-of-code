package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.testing.testDay
import kotlin.test.Test

class Day11Test {

    @Test
    fun testInput() = testDay(Day11::class.java, 1721, 298)

    @Test
    fun testExample() = testDay(Day11::class.java, 1656, 195, true)
}