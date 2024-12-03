package $package.days

import nl.sbmf21.aoc.common.TODO
import nl.sbmf21.aoc.testing.testDay
import org.junit.jupiter.api.Test

class Day${day}Test {

    @Test
    fun testInput() = testDay(Day${day}::class.java, TODO, TODO)

    @Test
    fun testExample() = testDay(Day${day}::class.java, TODO, TODO, true)
}