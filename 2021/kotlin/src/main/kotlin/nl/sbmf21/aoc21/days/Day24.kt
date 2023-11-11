package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay

class Day24(input: List<String>) : ADay(input) {

    private val instructions = input
        .filterIndexed { index, _ -> index % 18 == 15 || index % 18 == 5 }
        .map { it.split(" ", limit = 3)[2].toInt() }
    private val keys = (0..13).map(::Instruction)

    override fun part1() = calculate(9) { it >= 0 }
    override fun part2() = calculate(1) { it < 0 }

    private fun calculate(reset: Int, check: (Int) -> Boolean): String {
        val monad = IntArray(14)
        val stack = mutableListOf<Instruction>()

        keys.forEach { current ->
            if (current.x >= 10) {
                stack.add(current)
                return@forEach
            }

            val previous = stack.removeLast()
            val value = current.x + previous.y

            if (check(value)) {
                monad[current.id] = reset
                monad[previous.id] = monad[current.id] - value
            } else {
                monad[previous.id] = reset
                monad[current.id] = monad[previous.id] + value
            }
        }

        return monad.joinToString("")
    }

    private inner class Instruction(val id: Int) {
        val x = read(0)
        val y = read(1)
        private fun read(index: Int) = instructions[2 * id + index]
    }
}