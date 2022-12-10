package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc.common.aocLetter
import kotlin.math.floor

class Day10(input: List<String>) : ADay(input) {

    override fun part1(): Any {
        var strength = 0
        var check = 20

        run { cycle, x ->
            if (cycle + 1 == check) {
                strength += check * x
                check += 40
            }
        }

        return strength
    }

    override fun part2(): Any {
        val crt = List(6) { MutableList(40) { '.' } }

        run { cycle, x ->
            val row = floor(cycle / 40f).toInt()
            val i = cycle % 40
            crt[row][i] = if (i in x - 1..x + 1) '#' else '.'
        }

        println(crt.joinToString("\n") { it.joinToString("") })
        return crt
            .foldIndexed(List(8) { List(6) { MutableList(5) { 0 } } }) { y, letters, chars ->
                chars.forEachIndexed { lineX, char ->
                    val index = (lineX / 5f).toInt()
                    val x = lineX % 5
                    letters[index][y][x] = if (char == '#') 1 else 0
                }
                letters
            }
            .map { aocLetter(it) ?: '_' }
            .joinToString("")
    }

    private fun run(check: (cycle: Int, x: Int) -> Unit) {
        var cycles = 0
        var x = 1

        val ops = ArrayDeque(input)
        var next: (() -> Unit)? = null

        while (ops.isNotEmpty() || next != null) {
            check.invoke(cycles++, x)

            if (next != null) {
                next.invoke()
                next = null
            } else {
                val op = ops.removeFirst()
                if (op == "noop") continue
                else next = { x += op.split(" ")[1].toInt() }
            }
        }
    }
}
