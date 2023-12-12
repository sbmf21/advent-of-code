package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Color
import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test

class Day12Test {

    @Test
    fun testInput() = testDay(Day12::class.java, 6958, Color.RED + "# TODO #" + Color.RESET)

    @Test
    fun testExample() = testDay(Day12::class.java, 21, Color.RED + "# TODO #" + Color.RESET /* 525_152 */, true)
}