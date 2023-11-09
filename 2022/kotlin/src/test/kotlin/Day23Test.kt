import nl.sbmf21.aoc22.days.Day23
import org.junit.jupiter.api.Test

class Day23Test {

    @Test
    fun testInput() {
        testDay(Day23::class.java, 3882, 1116)
    }

    @Test
    fun testExample() {
        testDay(Day23::class.java, 110, 20, true)
    }
}