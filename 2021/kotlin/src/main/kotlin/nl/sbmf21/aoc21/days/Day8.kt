package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay

class Day8(input: List<String>) : ADay(input) {

    private val lines = input.map { it.split("|") }
        .map { Pair(it[0].trim().split(" "), it[1].trim().split(" ")) }

    override fun part1() = lines
        .map { it.second.count { d -> d.length == 2 || d.length == 4 || d.length == 3 || d.length == 7 } }.sumOf { it }

    override fun part2() = lines.map { line ->
        val input = line.first

        val one = input.first { it.length == 2 }
        val four = input.first { it.length == 4 }
        val seven = input.first { it.length == 3 }
        val eight = input.first { it.length == 7 }

        val six = input.first { it.length == 6 && isSix(it.toCharArray(), one.toCharArray()) }
        val nine = input.filter { it.length == 6 }
            .filter { it != six }
            .first { matchesFour(it.toCharArray(), four.toCharArray()) }
        val zero = input.filter { it.length == 6 }.first { it != six && it != nine }

        val topRight = eight.toCharArray().first { !six.contains(it) }
        val bottomRight = one.toCharArray().first { it != topRight }

        val five = input.first { it.length == 5 && !it.contains(topRight) }
        val two = input.first { it.length == 5 && !it.contains(bottomRight) }
        val three = input.first { it.length == 5 && it != five && it != two }

        val top = seven.toCharArray().first { !one.contains(it) }
        val middle = eight.toCharArray().first { !zero.contains(it) }
        val bottom = three.toCharArray().filter { it != top && it != middle }.first { !one.contains(it) }

        val topLeft = four.toCharArray().first { it != middle && it != topRight && it != bottomRight }
        val bottomLeft = eight.toCharArray().first { !nine.contains(it) }

        line.second.map {
            if (it.length == 6 && !it.contains(middle)) 0
            else if (it.length == 2) 1
            else if (it.length == 5 && it.contains(topRight) && it.contains(bottomLeft)) 2
            else if (it.length == 5 && it.contains(topRight) && it.contains(bottomRight)) 3
            else if (it.length == 4) 4
            else if (it.length == 5 && it.contains(topLeft) && it.contains(bottomRight)) 5
            else if (it.length == 6 && !it.contains(topRight)) 6
            else if (it.length == 3) 7
            else if (it.length == 7) 8
            else if (it.length == 6 && !it.contains(bottomLeft)) 9
            else throw IllegalArgumentException("YEET")
        }.joinToString("") { it.toString() }.toInt()
    }.sumOf { it }

    private fun isSix(sixDigits: CharArray, oneDigits: CharArray) =
        (sixDigits.contains(oneDigits[0]) && !sixDigits.contains(oneDigits[1]))
                || (sixDigits.contains(oneDigits[1]) && !sixDigits.contains(oneDigits[0]))

    private fun matchesFour(nineDigits: CharArray, fourDigits: CharArray) =
        nineDigits.filter { fourDigits.contains(it) }.size == fourDigits.size
}
