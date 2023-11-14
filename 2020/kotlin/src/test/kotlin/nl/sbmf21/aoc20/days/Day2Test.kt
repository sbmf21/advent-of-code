package nl.sbmf21.aoc20.days

import nl.sbmf21.aoc20.testDay
import org.junit.jupiter.api.Test

class Day2Test {

    @Test
    fun testInput() = testDay(Day2::class.java, 515, 711)

    @Test
    fun testExample() = testDay(Day2::class.java, 2, 1, true)
}