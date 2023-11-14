package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc22.days.Day25.Companion.decode
import nl.sbmf21.aoc22.days.Day25.Companion.encode
import nl.sbmf21.aoc22.testDay
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day25Test {

    @Test
    fun testInput() {
        testDay(Day25::class.java, "2-2=21=0021=-02-1=-0", 50)
    }

    @Test
    fun testExample() {
        testDay(Day25::class.java, "2=-1=0", 50, true)
    }

    @Test
    fun testSnafu() = mapOf(
        1 to "1",
        2 to "2",
        3 to "1=",
        4 to "1-",
        5 to "10",
        6 to "11",
        7 to "12",
        8 to "2=",
        9 to "2-",
        10 to "20",
        15 to "1=0",
        20 to "1-0",
        2022 to "1=11-2",
        12345 to "1-0---0",
        314159265 to "1121-1110-1=0",
        1747 to "1=-0-2",
        906 to "12111",
        198 to "2=0=",
        11 to "21",
        201 to "2=01",
        31 to "111",
        1257 to "20012",
        32 to "112",
        353 to "1=-1=",
        107 to "1-12",
        7 to "12",
        3 to "1=",
        37 to "122",
    ).forEach { (decimal, snafu) ->
        assertEquals(decimal.toLong(), decode(snafu))
        assertEquals(snafu, encode(decimal.toLong()))
    }
}