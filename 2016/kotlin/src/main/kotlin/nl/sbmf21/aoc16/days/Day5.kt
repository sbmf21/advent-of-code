package nl.sbmf21.aoc16.days

import nl.sbmf21.aoc.common.Day
import java.math.BigInteger
import java.security.MessageDigest

class Day5 : Day() {

    private val doorId = input[0]

    override fun part1(): String {
        var password = ""

        solve(
            handle = { password += it[5] },
            checkNext = { password.length < 8 },
        )

        return password
    }

    override fun part2(): String {
        val password = mutableMapOf<Int, Char>()

        solve(
            check = { validIndex(it[5], password) },
            handle = { password += it[5].toString().toInt() to it[6] },
            checkNext = { password.size < 8 },
        )

        return password.entries
            .sortedBy { it.key }
            .joinToString("") { "${it.value}" }
    }

    private fun solve(check: (String) -> Boolean = { true }, handle: (String) -> Unit, checkNext: () -> Boolean) {
        var index = 0

        while (checkNext()) {
            val hash = md5("$doorId$index")

            if (hash.startsWith(PREFIX) && check(hash)) {
                handle(hash)
            }

            index++
        }
    }

    private fun md5(input: String) =
        BigInteger(1, DIGEST.digest(input.toByteArray())).toString(16).padStart(32, '0')

    private fun validIndex(index: Char, password: Map<Int, Char>) =
        index in INDICES && index.toString().toInt() !in password

    private companion object {
        val DIGEST: MessageDigest = MessageDigest.getInstance("MD5")
        val PREFIX = "0".repeat(5)
        val INDICES = '0'..'7'
    }
}