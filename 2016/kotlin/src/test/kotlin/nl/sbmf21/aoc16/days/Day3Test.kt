package nl.sbmf21.aoc16.days

import nl.sbmf21.aoc.testing.buildWithInput
import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day3Test {

    @Test
    fun testInput() = testDay(Day3::class.java, 862, 1577)

    @Test
    fun testExamplePart1() = assertEquals(
        0,
        buildWithInput(Day3::class.java, listOf("5 10 25")).part1(),
    )

    @Test
    fun testExamplePart2() = assertEquals(
        6,
        buildWithInput(
            Day3::class.java, listOf(
                "101 301 501",
                "102 302 502",
                "103 303 503",
                "201 401 601",
                "202 402 602",
                "203 403 603",
            )
        ).part2(),
    )
}