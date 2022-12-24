import nl.sbmf21.aoc22.days.Day22
import org.junit.jupiter.api.Test

class Day22Test {

    @Test
    fun testInput() {
        testDay(Day22::class.java, 149_250, -1)
    }

    @Test
    fun testExample() {
        testDay(Day22::class.java, 6032, -1, true)
    }
}
