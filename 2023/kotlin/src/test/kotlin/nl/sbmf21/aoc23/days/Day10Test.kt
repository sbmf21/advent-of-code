package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.testing.buildWithInput
import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day10Test {

    @Test
    fun testInput() = testDay(Day10::class.java, 6979, 443)

    @Test
    fun testExample1() = assertEquals(
        4,
        buildWithInput(
            Day10::class.java, """
                -L|F7
                7S-7|
                L|7||
                -L-J|
                L|-JF
            """.trimIndent().split("\n")
        ).part1(),
    )

    @Test
    fun testExample2() = assertEquals(
        8,
        buildWithInput(
            Day10::class.java, """
                ..F7.
                .FJ|.
                SJ.L7
                |F--J
                LJ...
            """.trimIndent().split("\n")
        ).part1(),
    )

    @Test
    fun testExample3() = assertEquals(
        4,
        buildWithInput(
            Day10::class.java, """
                ...........
                .S-------7.
                .|F-----7|.
                .||.....||.
                .||.....||.
                .|L-7.F-J|.
                .|..|.|..|.
                .L--J.L--J.
                ...........
            """.trimIndent().split("\n")
        ).part2(),
    )

    @Test
    fun testExample4() = assertEquals(
        4,
        buildWithInput(
            Day10::class.java, """
                ..........
                .S------7.
                .|F----7|.
                .||....||.
                .||....||.
                .|L-7F-J|.
                .|..||..|.
                .L--JL--J.
                ..........
            """.trimIndent().split("\n")
        ).part2(),
    )

    @Test
    fun testExample5() = assertEquals(
        8,
        buildWithInput(
            Day10::class.java, """
                .F----7F7F7F7F-7....
                .|F--7||||||||FJ....
                .||.FJ||||||||L7....
                FJL7L7LJLJ||LJ.L-7..
                L--J.L7...LJS7F-7L7.
                ....F-J..F7FJ|L7L7L7
                ....L7.F7||L7|.L7L7|
                .....|FJLJ|FJ|F7|.LJ
                ....FJL-7.||.||||...
                ....L---J.LJ.LJLJ...
            """.trimIndent().split("\n")
        ).part2(),
    )

    @Test
    fun testExample6() = assertEquals(
        10,
        buildWithInput(
            Day10::class.java, """
                FF7FSF7F7F7F7F7F---7
                L|LJ||||||||||||F--J
                FL-7LJLJ||||||LJL-77
                F--JF--7||LJLJ7F7FJ-
                L---JF-JLJ.||-FJLJJ7
                |F|F-JF---7F7-L7L|7|
                |FFJF7L7F-JF7|JL---7
                7-L-JL7||F7|L7F-7F7|
                L.L7LFJ|||||FJL7||LJ
                L7JLJL-JLJLJL--JLJ.L
            """.trimIndent().split("\n")
        ).part2(),
    )
}