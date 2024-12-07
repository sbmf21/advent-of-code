package je.bouk.aoc24.days

import nl.sbmf21.aoc.testing.testDay
import kotlin.test.Test

class Day7Test {

    @Test
    fun testInput() = testDay(Day7::class.java, 2_501_605_301_465L, 44_841_372_855_953L)

    @Test
    fun testExample() = testDay(Day7::class.java, 3_749L, 11_387L, true)
}