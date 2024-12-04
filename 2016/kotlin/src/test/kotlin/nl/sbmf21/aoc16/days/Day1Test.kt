package nl.sbmf21.aoc16.days

import nl.sbmf21.aoc.testing.buildWithInput
import nl.sbmf21.aoc.testing.testDay
import kotlin.test.Test
import kotlin.test.assertEquals

class Day1Test {

    @Test
    fun testInput() = testDay(Day1::class.java, 279, 163)

    @Test
    fun testExample1() = assertEquals(
        5,
        buildWithInput(Day1::class.java, listOf("R2, L3")).part1(),
    )

    @Test
    fun testExample2() = assertEquals(
        2,
        buildWithInput(Day1::class.java, listOf("R2, R2, R2")).part1(),
    )

    @Test
    fun testExample3() = assertEquals(
        12,
        buildWithInput(Day1::class.java, listOf("R5, L5, R5, R3")).part1(),
    )

    @Test
    fun testExample4() = assertEquals(
        4,
        buildWithInput(Day1::class.java, listOf("R8, R4, R4, R8")).part2(),
    )
}