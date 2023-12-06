package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.testing.buildWithInput
import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day1Test {

    @Test
    fun testInput() = testDay(Day1::class.java, 55029, 55686)

    @Test
    fun testExample() {
        assertEquals(
            142,
            buildWithInput(
                Day1::class.java,
                listOf(
                    "1abc2",
                    "pqr3stu8vwx",
                    "a1b2c3d4e5f",
                    "treb7uchet",
                ),
            ).part1(),
        )

        assertEquals(
            281,
            buildWithInput(
                Day1::class.java,
                listOf(
                    "two1nine",
                    "eightwothree",
                    "abcone2threexyz",
                    "xtwone3four",
                    "4nineeightseven2",
                    "zoneight234",
                    "7pqrstsixteen",
                ),
            ).part2(),
        )
    }
}