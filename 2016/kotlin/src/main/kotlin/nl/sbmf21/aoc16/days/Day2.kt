package nl.sbmf21.aoc16.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.math.Vector2i
import nl.sbmf21.math.by

class Day2 : Day() {

    override fun part1() = solve(1 by 1, NUMPAD) { it.y * 3 + it.x + 1 }
    override fun part2() = solve(0 by 2, BATHROOM_KEYPAD.keys) { BATHROOM_KEYPAD[it]!! }

    private fun solve(initial: Vector2i, valid: Set<Vector2i>, digit: (Vector2i) -> Any) = input
        .fold(initial to "") { (start, code), line ->
            var pos = start

            line.forEach { c ->
                when (c) {
                    'U' -> pos = next(pos, valid) { it - MOVE_Y }
                    'D' -> pos = next(pos, valid) { it + MOVE_Y }
                    'L' -> pos = next(pos, valid) { it - MOVE_X }
                    'R' -> pos = next(pos, valid) { it + MOVE_X }
                }
            }

            pos to (code + digit(pos))
        }
        .second

    private fun next(current: Vector2i, valid: Set<Vector2i>, next: (Vector2i) -> Vector2i): Vector2i {
        val check = next(current)
        return if (check in valid) check else current
    }

    private companion object {
        val MOVE_X = 1 by 0
        val MOVE_Y = 0 by 1

        private val NUMPAD = buildSet { for (y in 0..<3) for (x in 0..<3) add(x by y) }
        private val BATHROOM_KEYPAD = mapOf(
            2 by 0 to '1',

            1 by 1 to '2',
            2 by 1 to '3',
            3 by 1 to '4',

            0 by 2 to '5',
            1 by 2 to '6',
            2 by 2 to '7',
            3 by 2 to '8',
            4 by 2 to '9',

            1 by 3 to 'A',
            2 by 3 to 'B',
            3 by 3 to 'C',

            2 by 4 to 'D',
        )
    }
}