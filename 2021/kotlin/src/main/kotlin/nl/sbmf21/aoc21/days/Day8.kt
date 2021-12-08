package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay

/*
 * Here are all the calculated digits for part 2. I don't need them all, so I have removed them from the code.
 * But I am too proud of all of these to completely delete them :').
 *
 * val one = input.first { it.length == 2 }
 * val four = input.first { it.length == 4 }
 * val seven = input.first { it.length == 3 }
 * val eight = input.first { it.length == 7 }
 *
 * val six = input.first { it.length == 6 && isSix(it.toCharArray(), one.toCharArray()) }
 * val nine = input.filter { it.length == 6 }.filter { it != six }.first { matchesFour(it.toCharArray(), four.toCharArray()) }
 * val zero = input.filter { it.length == 6 }.first { it != six && it != nine }
 *
 * val topRight = eight.toCharArray().first { !six.contains(it) }
 * val bottomRight = one.toCharArray().first { it != topRight }
 *
 * val five = input.first { it.length == 5 && !it.contains(topRight) }
 * val two = input.first { it.length == 5 && !it.contains(bottomRight) }
 * val three = input.first { it.length == 5 && it != five && it != two }
 *
 * val top = seven.toCharArray().first { !one.contains(it) }
 * val middle = eight.toCharArray().first { !zero.contains(it) }
 * val bottom = three.toCharArray().filter { it != top && it != middle }.first { !one.contains(it) }
 *
 * val topLeft = four.toCharArray().first { it != middle && it != topRight && it != bottomRight }
 * val bottomLeft = eight.toCharArray().first { !nine.contains(it) }
 */

class Day8(input: List<String>) : ADay(input) {

    private val lines = input.map { it.split("|") }
        .map { Pair(it[0].trim().split(" "), it[1].trim().split(" ")) }

    override fun part1() = run { it.second.count { d -> listOf(2, 4, 3, 7).contains(d.length) } }

    override fun part2() = run { line ->
        val one = line.first.first { it.length == 2 }
        val four = line.first.first { it.length == 4 }
        val eight = line.first.first { it.length == 7 }

        val six = line.first.first { it.length == 6 && isSix(it.toCharArray(), one.toCharArray()) }
        val nine = line.first.filter { it.length == 6 }
            .filter { it != six }
            .first { matchesFour(it.toCharArray(), four.toCharArray()) }
        val zero = line.first.filter { it.length == 6 }.first { it != six && it != nine }

        val m = eight.toCharArray().first { !zero.contains(it) }
        val tr = eight.toCharArray().first { !six.contains(it) }
        val br = one.toCharArray().first { it != tr }
        val tl = four.toCharArray().first { it != m && it != tr && it != br }
        val bl = eight.toCharArray().first { !nine.contains(it) }

        line.second.joinToString("") {
            if (it.length == 6 && !it.contains(m)) "0"
            else if (it.length == 2) "1"
            else if (it.length == 5 && it.contains(tr) && it.contains(bl)) "2"
            else if (it.length == 5 && it.contains(tr) && it.contains(br)) "3"
            else if (it.length == 4) "4"
            else if (it.length == 5 && it.contains(tl) && it.contains(br)) "5"
            else if (it.length == 6 && !it.contains(tr)) "6"
            else if (it.length == 3) "7"
            else if (it.length == 7) "8"
            else if (it.length == 6 && !it.contains(bl)) "9"
            else ""
        }.toInt()
    }

    private fun isSix(sixDigits: CharArray, oneDigits: CharArray) =
        (sixDigits.contains(oneDigits[0]) && !sixDigits.contains(oneDigits[1]))
                || (sixDigits.contains(oneDigits[1]) && !sixDigits.contains(oneDigits[0]))

    private fun matchesFour(nineDigits: CharArray, fourDigits: CharArray) =
        nineDigits.filter { fourDigits.contains(it) }.size == fourDigits.size

    private fun run(map: (Pair<List<String>, List<String>>) -> Int) = lines.map { line -> map(line) }.sumOf { it }
}
