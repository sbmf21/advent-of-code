package nl.sbmf21.aoc16.days

import nl.sbmf21.aoc.testing.buildWithInput
import nl.sbmf21.aoc.testing.testDay
import kotlin.test.Test
import kotlin.test.assertEquals

class Day9Test {

    @Test
    fun testInput() = testDay(Day9::class.java, 138_735L, 11_125_026_826L)

    @Test
    fun testExample1() = assertEquals(
        6L,
        buildWithInput(Day9::class.java, listOf("ADVENT")).part1(),
    )

    @Test
    fun testExample2() = assertEquals(
        7L,
        buildWithInput(Day9::class.java, listOf("A(1x5)BC")).part1(),
    )

    @Test
    fun testExample3() = assertEquals(
        9L,
        buildWithInput(Day9::class.java, listOf("(3x3)XYZ")).part1(),
    )

    @Test
    fun testExample4() = assertEquals(
        11L,
        buildWithInput(Day9::class.java, listOf("A(2x2)BCD(2x2)EFG")).part1(),
    )

    @Test
    fun testExample5() = assertEquals(
        6L,
        buildWithInput(Day9::class.java, listOf("(6x1)(1x3)A")).part1(),
    )

    @Test
    fun testExample6() = assertEquals(
        18L,
        buildWithInput(Day9::class.java, listOf("X(8x2)(3x3)ABCY")).part1(),
    )

    @Test
    fun testExample7() = assertEquals(
        9L,
        buildWithInput(Day9::class.java, listOf("(3x3)XYZ")).part2(),
    )

    @Test
    fun testExample8() = assertEquals(
        20L,
        buildWithInput(Day9::class.java, listOf("X(8x2)(3x3)ABCY")).part2(),
    )

    @Test
    fun testExample9() = assertEquals(
        241920L,
        buildWithInput(Day9::class.java, listOf("(27x12)(20x12)(13x14)(7x10)(1x12)A")).part2(),
    )

    @Test
    fun testExample10() = assertEquals(
        445L,
        buildWithInput(Day9::class.java, listOf("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN")).part2(),
    )
}
