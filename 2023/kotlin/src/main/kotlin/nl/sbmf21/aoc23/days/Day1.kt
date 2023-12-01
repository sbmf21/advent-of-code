package nl.sbmf21.aoc23.days

import nl.sbmf21.aoc.common.Day

class Day1 : Day() {

    override fun part1() = check(::getDigit)
    override fun part2() = check(::checkNumberAt)

    private fun check(check: (String, Int) -> Char?) = input.sumOf {
        "${left(it, check)}${right(it, check)}".toInt()
    }

    private fun left(input: String, check: (String, Int) -> Char?): Char {
        for (i in input.indices) check(input, i)?.let { return it }
        throw Error("no number found from the left")
    }

    private fun right(input: String, check: (String, Int) -> Char?): Char {
        for (i in input.lastIndex downTo 0) check(input, i)?.let { return it }
        throw Error("no number found from the right")
    }

    private fun checkNumberAt(input: String, index: Int): Char? {
        val check = input.substring(index)
        return when {
            check.startsWith("one") -> return '1'
            check.startsWith("two") -> return '2'
            check.startsWith("three") -> return '3'
            check.startsWith("four") -> return '4'
            check.startsWith("five") -> return '5'
            check.startsWith("six") -> return '6'
            check.startsWith("seven") -> return '7'
            check.startsWith("eight") -> return '8'
            check.startsWith("nine") -> return '9'
            else -> getDigit(input, index)
        }
    }

    private fun getDigit(input: String, index: Int) =
        "${input[index]}".let { if (it.matches(numberRegex)) it[0] else null }

    private companion object {
        val numberRegex = Regex("\\d")
    }
}