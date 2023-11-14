package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc21.testDay
import org.junit.jupiter.api.Test

class Day8Test {

    @Test
    fun testInput() = testDay(Day8::class.java, 532, 1_011_284)

    @Test
    fun testExample() = testDay(Day8::class.java, 26, 61_229, true)

    @Test
    fun testSingleExample() = testDay(Day8::class.java, 0, 5353, true, "single")
}