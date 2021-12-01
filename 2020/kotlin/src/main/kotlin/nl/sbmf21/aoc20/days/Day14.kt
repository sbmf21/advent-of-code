package nl.sbmf21.aoc20.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc20.Aoc
import java.lang.Long.toBinaryString
import java.math.BigInteger
import java.util.regex.Pattern

class Day14(aoc: Aoc, number: Int) : ADay(aoc, number) {

    private val regex = Pattern.compile("(?<cmd>mask|mem)(?:\\[(?<mem>\\d+)])? = (?<val>[\\dX]+)")

    override fun part1(): Long {
        var mask: String? = null
        val mem = mutableMapOf<Int, Long>()

        input.forEach {
            val matcher = regex.matcher(it)
            matcher.matches()

            when (matcher.group("cmd")) {
                "mask" -> mask = matcher.group("val")
                "mem" -> {
                    var value = toBinaryString(matcher.group("val").toLong())
                    value = String.format("%s%s", "0".repeat(36 - value.length), value)
                    value = applyMask(value, mask!!)
                    mem[matcher.group("mem").toInt()] = BigInteger(value, 2).toLong()
                }
            }
        }

        return mem.map { it.value }.sum()
    }

    override fun part2(): Long {
        var mask: String? = null
        val mem = mutableMapOf<Long, Long>()

        input.forEach {
            val matcher = regex.matcher(it)
            matcher.matches()

            when (matcher.group("cmd")) {
                "mask" -> mask = matcher.group("val")
                "mem" -> {
                    var memi = toBinaryString(matcher.group("mem").toLong())
                    memi = String.format("%s%s", "0".repeat(36 - memi.length), memi)
                    memi = applyMaskToMem(memi, mask!!)

                    addresses(memi).forEach { cmem ->
                        mem[BigInteger(cmem, 2).toLong()] = matcher.group("val").toLong()
                    }
                }
            }
        }

        return mem.map { it.value }.sum()
    }

    private fun addresses(value: String): List<String> {

        val list = mutableListOf<String>()

        val newVal = value.toCharArray()
        val indices = value.toCharArray().filter { it == 'X' }.size
        val num = BigInteger("1".repeat(indices), 2).toLong()

        for (i in 0..num) {
            var numi = toBinaryString(i)
            numi = String.format("%s%s", "0".repeat(indices - numi.length), numi)
            val chars = numi.toCharArray()
            var c = 0

            list.add(newVal.map {
                if (it == 'X') {
                    val v = chars[c]
                    c++
                    return@map v
                }

                it
            }.joinToString(""))
        }

        return list
    }

    private fun applyMask(value: String, mask: String): String {

        val newVal = value.toCharArray()
        mask.toCharArray().forEachIndexed { index, it ->
            when (it) {
                '0', '1' -> newVal[index] = it
            }
        }

        return newVal.joinToString("")
    }

    private fun applyMaskToMem(value: String, mask: String): String {

        val newVal = value.toCharArray()
        mask.toCharArray().forEachIndexed { index, it ->
            when (it) {
                'X', '1' -> newVal[index] = it
            }
        }

        return newVal.joinToString("")
    }
}