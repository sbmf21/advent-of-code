package nl.sbmf21.aoc.common

import org.junit.jupiter.api.Test
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

    @Test
    fun testTriangular() {
        /* Short  */ assertEquals(105, 14.toShort().triangular())
        /* Int    */ assertEquals(153, 17.triangular())
        /* Long   */ assertEquals(248946141, 22313.toLong().triangular())
        /* Float  */ assertEquals(110.88F, 14.4F.triangular())
        /* Double */ assertEquals(6.307287406041018E9, 112314.124214.triangular())
        /* UInt   */ assertEquals(6.307287406041018E9, 112314.124214.triangular())
        /* UInt   */ assertEquals(153u, 17u.triangular())
        /* ULong  */ assertEquals(248946141u, 22313u.toULong().triangular())
    }
}