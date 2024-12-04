package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.testing.testDay
import kotlin.test.Test

class Day3Test {

    @Test
    fun testInput() = testDay(Day3::class.java, 4_118_544, 3_832_770)

    @Test
    fun testExample() = testDay(Day3::class.java, 198, 230, true)
}