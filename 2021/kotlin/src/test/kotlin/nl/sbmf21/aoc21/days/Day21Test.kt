package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.testing.testDay
import kotlin.test.Test

class Day21Test {

    @Test
    fun testInput() = testDay(Day21::class.java, 506_466, 6_329_79_211_251_440)

    @Test
    fun testExample() = testDay(Day21::class.java, 739_785, 444_356_092_776_315, true)
}