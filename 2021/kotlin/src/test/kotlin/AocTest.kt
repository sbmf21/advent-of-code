import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc.common.buildDay
import nl.sbmf21.aoc21.Aoc
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AocTest {

    @Test
    fun names() = Aoc().findDays().forEach { day -> assertTrue(day.simpleName.matches(Regex("Day\\d+"))) }
}

fun testDay(cls: Class<out ADay>, part1: Int, part2: Int, example: Boolean = false) {
    val day = buildDay(cls, example)

    assertEquals(part1, day.part1())
    assertEquals(part2, day.part2())
}
