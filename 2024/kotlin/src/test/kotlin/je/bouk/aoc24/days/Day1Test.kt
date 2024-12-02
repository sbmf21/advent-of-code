package je.bouk.aoc24.days

import nl.sbmf21.aoc.testing.testDay
import kotlin.test.Test

class Day1Test {

    @Test
    fun testInput() = testDay(Day1::class.java, 2_176_849, 23_384_288)

    @Test
    fun testExample() = testDay(Day1::class.java, 11, 31, true)
}