import nl.sbmf21.aoc21.days.Day16
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day16Test {

    @Test
    fun testInput() = testDay(Day16::class.java, 879, 539_051_801_941)

    @Test
    fun testPart1_1() = assertEquals(16, buildHex("8A004A801A8002F478").part1())

    @Test
    fun testPart1_2() = assertEquals(12, buildHex("620080001611562C8802118E34").part1())

    @Test
    fun testPart1_3() = assertEquals(23, buildHex("C0015000016115A2E0802F182340").part1())

    @Test
    fun testPart1_4() = assertEquals(31, buildHex("A0016C880162017C3686B18A3D4780").part1())

    @Test
    fun testPart2_1() = assertEquals(3L, buildHex("C200B40A82").part2())

    @Test
    fun testPart2_2() = assertEquals(54L, buildHex("04005AC33890").part2())

    @Test
    fun testPart2_3() = assertEquals(7L, buildHex("880086C3E88112").part2())

    @Test
    fun testPart2_4() = assertEquals(9L, buildHex("CE00C43D881120").part2())

    @Test
    fun testPart2_5() = assertEquals(1L, buildHex("D8005AC2A8F0").part2())

    @Test
    fun testPart2_6() = assertEquals(0L, buildHex("F600BC2D8F").part2())

    @Test
    fun testPart2_7() = assertEquals(0L, buildHex("9C005AC2F8F0").part2())

    @Test
    fun testPart2_8() = assertEquals(1L, buildHex("9C0141080250320F1802104A08").part2())

    private fun buildHex(hex: String) = buildWithInput(Day16::class.java, listOf(hex))
}