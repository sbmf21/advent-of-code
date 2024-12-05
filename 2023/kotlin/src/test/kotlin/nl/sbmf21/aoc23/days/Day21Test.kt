package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.TODO
import nl.sbmf21.aoc.testing.testDay
import nl.sbmf21.aoc23.days.Day21.Companion.STEP_1_STEPS
import nl.sbmf21.aoc23.days.Day21.Companion.STEP_1_STEPS_DEFAULT
import kotlin.test.Test

class Day21Test {

    @Test
    fun testInput() {
        STEP_1_STEPS = STEP_1_STEPS_DEFAULT
        testDay(Day21::class.java, 3830, TODO)
    }

    @Test
    fun testExample() {
        STEP_1_STEPS = 6
        testDay(Day21::class.java, 16, TODO, true)
    }
}