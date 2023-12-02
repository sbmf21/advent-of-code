package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc23.testDay
import org.junit.jupiter.api.Test

class Day2Test {

    @Test
    fun testInput() = testDay(Day2::class.java, 2727, 56580)

    @Test
    fun testExample() = testDay(Day2::class.java, 8, 2286, true)
}