import nl.sbmf21.aoc.common.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class EpicUtilTest {

    @Test
    fun testIterated() {
        val list = MutableList(50) { it }

        list.iterated { iter, it ->
            if (it % 2 == 1) {
                iter.remove()
            }
        }

        assertEquals(
            listOf(0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48),
            list
        )
    }

    @Test
    fun testMapToInts() = assertEquals(
        listOf(2, 214, 3, 5, 814, 456),
        listOf("2", "214", "3", "5", "814", "456").mapToInts()
    )

    @Test
    fun testMapToLongs() = assertEquals(
        listOf<Long>(2, 2145649731598, 3, 5, 8496584354890489, 456),
        listOf("2", "2145649731598", "3", "5", "8496584354890489", "456").mapToLongs()
    )

    @Test
    fun testMapToFloats() = assertEquals(
        listOf(2.5F, 214.125F, 3.412F, 5.124F, 814.2F, 456.0F),
        listOf("2.5", "214.125", "3.412", "5.124", "814.2", "456.0").mapToFloats()
    )

    @Test
    fun testMapToDoubles() = assertEquals(
        listOf(2.5, 214.125, 1.24125612312312336E17, 5.124, 814.2, 456.0),
        listOf("2.5", "214.125", "124125612312312343.412", "5.124", "814.2", "456.0").mapToDoubles()
    )
}
