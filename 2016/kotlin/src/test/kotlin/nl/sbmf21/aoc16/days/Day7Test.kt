package nl.sbmf21.aoc16.days

import nl.sbmf21.aoc.testing.buildWithInput
import nl.sbmf21.aoc.testing.testDay
import kotlin.test.Test
import kotlin.test.assertEquals

class Day7Test {

    // 455 too high
    // 15 no

    @Test
    fun testInput() = testDay(Day7::class.java, 110, 242)

    @Test
    fun testExample1() = assertEquals(
        1,
        buildWithInput(Day7::class.java, listOf("abba[mnop]qrst")).part1(),
    )

    @Test
    fun testExample2() = assertEquals(
        0,
        buildWithInput(Day7::class.java, listOf("abcd[bddb]xyyx")).part1(),
    )

    @Test
    fun testExample3() = assertEquals(
        0,
        buildWithInput(Day7::class.java, listOf("aaaa[qwer]tyui")).part1(),
    )

    @Test
    fun testExample4() = assertEquals(
        1,
        buildWithInput(Day7::class.java, listOf("ioxxoj[asdfgh]zxcvbn")).part1(),
    )

    @Test
    fun testExample5() = assertEquals(
        1,
        buildWithInput(Day7::class.java, listOf("aba[bab]xyz")).part2(),
    )

    @Test
    fun testExample6() = assertEquals(
        0,
        buildWithInput(Day7::class.java, listOf("xyx[xyx]xyx")).part2(),
    )

    @Test
    fun testExample7() = assertEquals(
        1,
        buildWithInput(Day7::class.java, listOf("aaa[kek]eke")).part2(),
    )

    @Test
    fun testExample8() = assertEquals(
        1,
        buildWithInput(Day7::class.java, listOf("zazbz[bzb]cdb")).part2(),
    )
}