package nl.sbmf21.aoc16.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.aocLetter
import nl.sbmf21.aoc16.days.Day8.Rotate.Direction.COLUMN
import nl.sbmf21.aoc16.days.Day8.Rotate.Direction.ROW
import nl.sbmf21.math.Vector2i

class Day8 : Day() {

    private val screen = buildList<Vector2i> {
        input.mapNotNull {
            when {
                Rect.matches(it) -> Rect.map(it)
                Rotate.matches(it) -> Rotate.map(it)
                else -> null
            }
        }.forEach {
            when (it) {
                is Rect -> for (x in 0 until it.width)
                    for (y in 0 until it.height)
                        this += Vector2i(x, y)

                is Rotate -> when (it.direction) {
                    ROW -> this.filter { p -> p.y == it.index }.forEach { p ->
                        p.x += it.length
                        p.x %= 50
                    }

                    COLUMN -> this.filter { p -> p.x == it.index }.forEach { p ->
                        p.y += it.length
                        p.y %= 6
                    }
                }
            }
        }
    }

    override fun part1() = screen.size

    override fun part2() = screen
        .fold(List(50 / 5) { List(6) { MutableList(5) { 0 } } }) { letters, point ->
            letters[point.x / 5][point.y][point.x % 5] = 1
            letters
        }
        .map(::aocLetter)
        .joinToString("")

    private class Rect private constructor(val width: Int, val height: Int) {
        companion object {
            private val REGEX = Regex("""rect (\d+)x(\d+)""")

            fun matches(check: String) = REGEX.matches(check)

            fun map(check: String): Rect {
                val match = REGEX.matchEntire(check)!!.groupValues
                return Rect(match[1].toInt(), match[2].toInt())
            }
        }
    }

    private class Rotate private constructor(val direction: Direction, val index: Int, val length: Int) {
        companion object {
            private val REGEX = Regex("""rotate (row|column) [xy]=(\d+) by (\d+)""")

            fun matches(check: String) = REGEX.matches(check)

            fun map(check: String): Rotate {
                val match = REGEX.matchEntire(check)!!.groupValues
                return Rotate(Direction(match[1]), match[2].toInt(), match[3].toInt())
            }
        }

        enum class Direction {
            ROW,
            COLUMN;

            companion object {
                operator fun invoke(value: String) = entries.first { it.name.lowercase() == value.lowercase() }
            }
        }
    }
}