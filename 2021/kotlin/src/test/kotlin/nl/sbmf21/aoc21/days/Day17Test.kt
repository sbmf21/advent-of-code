package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc21.testDay
import org.junit.jupiter.api.Test

class Day17Test {

    @Test
    fun testInput() = testDay(Day17::class.java, 7626, 2032)

    @Test
    fun testExample() = testDay(Day17::class.java, 45, 112, true)
}