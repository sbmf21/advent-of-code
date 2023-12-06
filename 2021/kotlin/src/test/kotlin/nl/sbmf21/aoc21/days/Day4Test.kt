package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test

class Day4Test {

    @Test
    fun testInput() = testDay(Day4::class.java, 55_770, 2980)

    @Test
    fun testExample() = testDay(Day4::class.java, 4512, 1924, true)
}