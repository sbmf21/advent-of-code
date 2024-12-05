package je.bouk.aoc24.days

import nl.sbmf21.aoc.testing.testDay
import kotlin.test.Test

class Day5Test {

    @Test
    fun testInput() = testDay(Day5::class.java, 6384, 5353)

    @Test
    fun testExample() = testDay(Day5::class.java, 143, 123, true)
}