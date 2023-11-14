package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc21.testDay
import org.junit.jupiter.api.Test

class Day12Test {

    @Test
    fun testInput() = testDay(Day12::class.java, 3761, 99_138)

    @Test
    fun testExample1() = testDay(Day12::class.java, 10, 36, true, "1")

    @Test
    fun testExample2() = testDay(Day12::class.java, 19, 103, true, "2")

    @Test
    fun testExample3() = testDay(Day12::class.java, 226, 3509, true, "3")
}