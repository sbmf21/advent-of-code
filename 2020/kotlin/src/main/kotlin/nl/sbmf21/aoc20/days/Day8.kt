package nl.sbmf21.aoc20.days

import nl.sbmf21.aoc.common.ADay
import java.util.regex.Pattern

class Day8(input: List<String>) : ADay(input) {

    private val pattern: Pattern = Pattern.compile("(?<cmd>acc|jmp|nop) (?<val>[-+]\\d+)")

    override fun part1(): Int {

        val seen = Array(input.size) { false }
        var acc = 0

        run(input) { index, cmd, value ->
            if (hasSeen(seen, index)) {
                return@run null // breaks the loop
            }

            when (cmd) {
                "acc" -> acc += value
                "jmp" -> return@run value
            }

            1
        }

        return acc
    }

    override fun part2(): Int {

        var acc = 0

        run(breakInfinity()) { _, cmd, value ->
            when (cmd) {
                "acc" -> acc += value
                "jmp" -> return@run value
            }

            1
        }

        return acc
    }

    private fun breakInfinity(): MutableList<String> {
        var jon = 0
        var lines: MutableList<String>

        do {
            jon = findJmpOrNop(jon)
            lines = change(jon)
        } while (isInfinite(lines))

        return lines
    }

    private fun findJmpOrNop(start: Int = 0): Int {
        val pattern = Pattern.compile("(jmp|nop).*")

        for (i in start + 1..input.size) {
            if (pattern.matcher(input[i]).matches()) {
                return i
            }
        }

        return -1
    }

    private fun change(index: Int): MutableList<String> {
        val lines = input.toMutableList()
        val matcher = pattern.matcher(lines[index])

        if (!matcher.matches()) {
            throw Error("Illegal line")
        }

        lines[index] = when (matcher.group("cmd")) {
            "jmp" -> "nop ${matcher.group("val")}"
            "nop" -> "jmp ${matcher.group("val")}"
            else -> lines[index]
        }

        return lines
    }

    private fun isInfinite(input: MutableList<String>): Boolean {
        val seen = Array(input.size) { false }
        var infiniteLineNum = -1

        run(input) { index, cmd, value ->
            if (hasSeen(seen, index)) {
                infiniteLineNum = index
                return@run null
            }

            if (cmd == "jmp") value else 1
        }

        return infiniteLineNum >= 0
    }

    private fun hasSeen(numsSeen: Array<Boolean>, index: Int): Boolean {
        if (numsSeen[index]) {
            return true
        }
        numsSeen[index] = true
        return false
    }

    private fun run(lines: List<String>, apply: (Int, String, Int) -> Int?) {
        var linenum = 0
        while (linenum < lines.size) {
            val matcher = pattern.matcher(lines[linenum]); matcher.matches()
            val value = apply(linenum, matcher.group("cmd"), matcher.group("val").toInt()) ?: break

            linenum += value
        }
    }
}