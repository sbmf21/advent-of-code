import org.junit.jupiter.api.Test
import standin.Aoc
import standin.days.*
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AocTest {

    private val aoc = Aoc()

    @Test
    fun testDay() {
        aoc.init(arrayOf("--day"), "7".byteInputStream())

        assertEquals(7, aoc.runDay)
    }

    @Test
    fun testDayNumber() {
        aoc.init(arrayOf("--day=2"))

        assertEquals(2, aoc.runDay)
    }

    @Test
    fun testLatest() {
        aoc.init(arrayOf("--latest"))

        assertEquals(23, aoc.runDay)
    }

    @Test
    fun testDaysCount() = assertEquals(4, aoc.days.size)

    @Test
    fun testDaysContainCorrectDays() {
        assertTrue(aoc.days.any { it.clazz.isAssignableFrom(Day1::class.java) })
        assertTrue(aoc.days.any { it.clazz.isAssignableFrom(Day2::class.java) })
        assertTrue(aoc.days.any { it.clazz.isAssignableFrom(Day7::class.java) })
        assertTrue(aoc.days.any { it.clazz.isAssignableFrom(Day23::class.java) })
    }

    @Test
    fun testDaysDoesNotContainWrongDays() {
        assertFalse(aoc.days.any { it.clazz.isAssignableFrom(NotADay::class.java) })
        assertFalse(aoc.days.any { it.clazz.isAssignableFrom(WrongDay::class.java) })
    }

    @Test
    fun testDayNumberOrders() {
        assertEquals(aoc.days[0].clazz, Day1::class.java)
        assertEquals(aoc.days[0].number, 1)

        assertEquals(aoc.days[1].clazz, Day2::class.java)
        assertEquals(aoc.days[1].number, 2)

        assertEquals(aoc.days[2].clazz, Day7::class.java)
        assertEquals(aoc.days[2].number, 7)

        assertEquals(aoc.days[3].clazz, Day23::class.java)
        assertEquals(aoc.days[3].number, 23)
    }

    @Test
    fun testInput() {
        val days = aoc.apply { runDay = null }.runDays()

        assertEquals(listOf("hi", "bye", "i", "am", "INPUT"), days.first { it is Day1 }.input)
        assertEquals(listOf("some", "stuff"), days.first { it is Day2 }.input)
        assertEquals(listOf(), days.first { it is Day7 }.input)
        assertEquals(listOf("epic,4"), days.first { it is Day23 }.input)
    }
}
