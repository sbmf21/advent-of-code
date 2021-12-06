import nl.sbmf21.aoc.common.chineseRemainder
import nl.sbmf21.aoc.common.mod
import nl.sbmf21.aoc.common.modInv
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

class EpicMathTest {

    /**
     * I have no clue whatsoever what this function actually does.
     * But as long as this works it's all fine.
     */
    @Test
    fun testChineseRemainder() = assertEquals(
        269,
        chineseRemainder(
            listOf(1, 5, 24, 7),
            listOf(213, 4, 5, 31)
        )
    )

    @ParameterizedTest(name = "mod({0}, {1}) should be {2}")
    @MethodSource("dataMod")
    fun testMod(a: Long, b: Long, output: Long) = assertEquals(output, mod(a, b))

    @ParameterizedTest(name = "modInv({0}, {1}) should be {2}")
    @MethodSource("dataModInv")
    fun testModInv(a: Long, b: Long, output: Long) = assertEquals(output, modInv(a, b))

    companion object {
        @JvmStatic
        fun dataMod() = Stream.of(
            Arguments.of(1, 7, 1),
            Arguments.of(2, 4, 2),
            Arguments.of(9, 2, 1),
            Arguments.of(4, 5, 4)
        )!!

        @JvmStatic
        fun dataModInv() = Stream.of(
            Arguments.of(1, 7, 1),
            Arguments.of(3, 4, 3),
            Arguments.of(9, 2, 1),
            Arguments.of(4, 5, 4)
        )!!
    }
}