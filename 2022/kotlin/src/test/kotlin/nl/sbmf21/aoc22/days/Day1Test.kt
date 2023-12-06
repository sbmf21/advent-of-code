package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test

class Day1Test {

    @Test
    fun testInput() = testDay(Day1::class.java, 71023, 206289)

    @Test
    fun testExample() = testDay(Day1::class.java, 24000, 45000, true)
}