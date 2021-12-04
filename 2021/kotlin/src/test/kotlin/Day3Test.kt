
import nl.sbmf21.aoc.common.file
import nl.sbmf21.aoc21.days.Day3
import org.junit.jupiter.api.Test

class Day3Test {

    private var day = Day3(file(Day3::class.java, false))

    @Test
    fun test() = testDay(Day3::class.java, 4118544, 3832770)

    @Test
    fun testExample() = testDay(Day3::class.java, 198, 230, true)
}
