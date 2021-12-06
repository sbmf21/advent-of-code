import nl.sbmf21.aoc21.days.Day6
import org.junit.jupiter.api.Test

class Day6Test {

    @Test
    fun test() = testDay(Day6::class.java, 374_927.0, 1_687_617_803_407.0) // 1687617803407

    @Test
    fun testExample() = testDay(Day6::class.java, 5_934.0, 26_984_457_539.0, true)
}
