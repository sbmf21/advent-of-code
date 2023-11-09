import nl.sbmf21.aoc22.days.Day1
import org.junit.jupiter.api.Test

class Day1Test {

    @Test
    fun testInput() = testDay(Day1::class.java, 71023, 206289)

    @Test
    fun testExample() = testDay(Day1::class.java, 24000, 45000, true)
}