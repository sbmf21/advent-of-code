package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.testing.buildWithInput
import nl.sbmf21.aoc.testing.testDay
import kotlin.test.Test
import kotlin.test.assertEquals

class Day18Test {

    @Test
    fun testInput() {
        testDay(Day18::class.java, 4390, 2534)
    }

    @Test
    fun testExampleSmall() {
        val day = buildWithInput(Day18::class.java, listOf("1,1,1", "2,1,1"))
        assertEquals(10, day.part1())
    }

    @Test
    fun testExample() {
        testDay(Day18::class.java, 64, 58, true)
    }
}