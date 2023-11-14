package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc21.testDay
import org.junit.jupiter.api.Test

class Day20Test {

    @Test
    fun testInput() = testDay(Day20::class.java, 5573, 20_097)

    @Test
    fun testExample() = testDay(Day20::class.java, 35, 3351, true)
}