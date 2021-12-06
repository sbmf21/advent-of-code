import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc.common.DayMeta
import nl.sbmf21.aoc21.Aoc
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AocTest {

    @Test
    fun names() = Aoc().days.forEach { day -> assertTrue(day.clazz.simpleName.matches(Regex("Day\\d+"))) }
}

fun <N : Number> testDay(clazz: Class<out ADay>, part1: N, part2: N, example: Boolean = false) {
    val day = DayMeta(clazz).build(example)

    assertEquals(part1, day.part1())
    assertEquals(part2, day.part2())
}
