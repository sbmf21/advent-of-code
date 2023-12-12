package nl.sbmf21.aoc16.days

import nl.sbmf21.aoc.testing.buildWithInput
import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day4Test {

    @Test
    fun testInput() = testDay(Day4::class.java, 185371, 984)

    @Test
    fun testExamplePart1() = assertEquals(
        1514,
        buildWithInput(
            Day4::class.java,
            listOf(
                "aaaaa-bbb-z-y-x-123[abxyz]",
                "a-b-c-d-e-f-g-h-987[abcde]",
                "not-a-real-room-404[oarel]",
                "totally-real-room-200[decoy]",
            ),
        ).part1(),
    )

    @Test
    fun testExamplePart2() = assertEquals(
        -1,
        buildWithInput(Day4::class.java, listOf("qzmt-zixmtkozy-ivhz-343[zimth]")).part2(),
    )
}