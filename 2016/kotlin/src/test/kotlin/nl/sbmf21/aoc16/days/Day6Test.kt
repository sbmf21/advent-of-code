package nl.sbmf21.aoc16.days

import nl.sbmf21.aoc.testing.testDay
import kotlin.test.Test

class Day6Test {

    @Test
    fun testInput() = testDay(Day6::class.java, "qzedlxso", "ucmifjae")

    @Test
    fun testExample() = testDay(Day6::class.java, "easter", "advent", true)
}