package nl.sbmf21.aoc16.days

import nl.sbmf21.aoc.common.Day

class Day7 : Day() {

    private val addresses = input.map(::mapIpv7Segments)

    override fun part1() = addresses.count(::supportsTls)
    override fun part2() = addresses.count(::supportsSsl)

    private fun mapIpv7Segments(ip: String) = buildList {
        var current = ""

        ip.forEach { c ->
            if (c == '[' || c == ']') {
                this += current
                current = ""
            } else current += c
        }

        if (current.isNotEmpty()) this += current
    }

    private fun supportsTls(value: List<String>): Boolean {
        fun testAbba(sub: String): Boolean {
            fun isAbba(test: String): Boolean {
                return test[0] != test[1]
                    && test[0] == test[3]
                    && test[2] == test[1]
            }

            for (i in 0..sub.length - 4) {
                val test = sub.substring(i..<i + 4)
                if (isAbba(test)) return true
            }

            return false
        }

        value.filterIndexed { i, _ -> i % 2 == 1 }.forEach {
            if (testAbba(it)) return false
        }

        value.filterIndexed { i, _ -> i % 2 == 0 }.forEach {
            if (testAbba(it)) return true
        }

        return false
    }

    private fun supportsSsl(value: List<String>): Boolean {
        val others = value.filterIndexed { i, _ -> i % 2 == 1 }

        fun testAba(sub: String): Boolean {
            fun isAba(test: String): Boolean {
                return test[0] != test[1]
                    && test[0] == test[2]
            }

            for (i in 0..sub.length - 3) {
                val test = sub.substring(i..<i + 3)
                if (isAba(test)) {
                    val expect = "${test[1]}${test[0]}${test[1]}"
                    if (others.any { it.contains(expect) }) return true
                }
            }

            return false
        }

        value.filterIndexed { i, _ -> i % 2 == 0 }.forEach {
            if (testAba(it)) return true
        }

        return false
    }
}