package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.aocLetter
import kotlin.math.floor

class Day10 : Day() {

    var store: ((
        cycle: Int,
        operation: String,
        operationPart: Int,
        x: Int,
        strength: Int,
        crt: List<List<Int>>,
        row: Int,
        i: Int,
    ) -> Unit)? = null

    override fun part1() = run().first

    override fun part2() = run().second
        .foldIndexed(List(8) { List(6) { MutableList(5) { 0 } } }) { y, letters, bits ->
            bits.forEachIndexed { lineX, bit ->
                val index = (lineX / 5f).toInt()
                val x = lineX % 5
                letters[index][y][x] = bit
            }
            letters
        }
        .map { aocLetter(it) ?: '_' }
        .joinToString("")

    fun run(): Pair<Int, List<MutableList<Int>>> {
        val crt = List(6) { MutableList(40) { 0 } }

        var cycle = 0
        var x = 1

        var strength = 0
        var check = 20

        val ops = ArrayDeque(input)
        lateinit var op: String
        var opPart: Int
        var next: (() -> Unit)? = null

        while (ops.isNotEmpty() || next != null) {
            val row = floor(cycle / 40f).toInt()
            val i = cycle % 40
            if (i in x - 1..x + 1) crt[row][i] = 1

            if (++cycle == check) {
                strength += check * x
                check += 40
            }

            if (next != null) {
                opPart = 0
                next.invoke()
                next = null
            } else {
                opPart = 1
                op = ops.removeFirst()
                if (op != "noop") next = { x += op.split(" ")[1].toInt() }
            }

            store?.invoke(cycle, op, opPart, x, strength, crt, row, i)
        }

        return strength to crt
    }
}