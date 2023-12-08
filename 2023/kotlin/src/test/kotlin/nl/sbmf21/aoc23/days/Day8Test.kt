package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.testing.buildWithInput
import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day8Test {

    @Test
    fun testInput() = testDay(Day8::class.java, 19199L, 13_663_968_099_527L)

    @Test
    fun testExample1() = assertEquals(
        2L,
        buildWithInput(
            Day8::class.java,
            listOf(
                "RL",
                "",
                "AAA = (BBB, CCC)",
                "BBB = (DDD, EEE)",
                "CCC = (ZZZ, GGG)",
                "DDD = (DDD, DDD)",
                "EEE = (EEE, EEE)",
                "GGG = (GGG, GGG)",
                "ZZZ = (ZZZ, ZZZ)",
            ),
        ).part1(),
    )

    @Test
    fun testExample2() = assertEquals(
        6L,
        buildWithInput(
            Day8::class.java,
            listOf(
                "LLR",
                "",
                "AAA = (BBB, BBB)",
                "BBB = (AAA, ZZZ)",
                "ZZZ = (ZZZ, ZZZ)",
            ),
        ).part1(),
    )

    @Test
    fun testExample3() = assertEquals(
        6L,
        buildWithInput(
            Day8::class.java,
            listOf(
                "LR",
                "",
                "11A = (11B, XXX)",
                "11B = (XXX, 11Z)",
                "11Z = (11B, XXX)",
                "22A = (22B, XXX)",
                "22B = (22C, 22C)",
                "22C = (22Z, 22Z)",
                "22Z = (22B, 22B)",
                "XXX = (XXX, XXX)",
            ),
        ).part2(),
    )
}