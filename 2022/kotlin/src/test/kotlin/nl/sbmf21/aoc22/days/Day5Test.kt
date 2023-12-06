package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test

class Day5Test {

    @Test
    fun testInput() = testDay(Day5::class.java, "VWLCWGSDQ", "TCGLQSLPW")

    @Test
    fun testExample() = testDay(Day5::class.java, "CMZ", "MCD", true)
}