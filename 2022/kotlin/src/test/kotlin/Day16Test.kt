import nl.sbmf21.aoc22.days.Day16
import org.junit.jupiter.api.Test

class Day16Test {

    init {
        Day16.DO_FAKE_RUN = true
    }

    @Test
    fun testInput() {
        testDay(Day16::class.java, 1647, -1)
    }

    @Test
    fun testExample() {
        testDay(Day16::class.java, 1651, -1, true)
    }
}