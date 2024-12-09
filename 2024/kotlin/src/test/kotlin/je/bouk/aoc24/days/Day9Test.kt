package je.bouk.aoc24.days

import nl.sbmf21.aoc.testing.testDay
import kotlin.test.Test

class Day9Test {

    @Test
    fun testInput() = testDay(Day9::class.java, 6_382_875_730_645L, 6_420_913_943_576L)

    @Test
    fun testExample() = testDay(Day9::class.java, 1_928L, 2_858L, true)
}