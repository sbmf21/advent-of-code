import nl.sbmf21.aoc22.days.Day21
import org.junit.jupiter.api.Test

class Day21Test {

    @Test
    fun testInput() {
        testDay(Day21::class.java, 21_208_142_603_224L, 3_882_224_466_191L)
    }

    @Test
    fun testExample() {
        testDay(Day21::class.java, 152L, 301L, true)
    }
}
