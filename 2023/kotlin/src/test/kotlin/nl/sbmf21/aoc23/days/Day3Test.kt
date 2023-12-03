package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc23.testDay
import org.junit.jupiter.api.Test

class Day3Test {

    @Test
    fun testInput() = testDay(Day3::class.java, 537_732, 84_883_664)

    @Test
    fun testExample() = testDay(Day3::class.java, 4_361, 467_835, true)
}