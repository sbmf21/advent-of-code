package nl.sbmf21.aoc.common.util

import nl.sbmf21.aoc.common.util.Direction.entries
import nl.sbmf21.math.Vector2i
import nl.sbmf21.math.Vector2l
import nl.sbmf21.math.by

/**
 * Direction for grid traversal.
 * Upwards are negative because the highest grid y will always be zero.
 * This is because we enumerate input lines by their index in the list.
 */
enum class Direction(
    val x: Int,
    val y: Int,
    opposite: () -> Direction,
    perpendicular: () -> Direction,
    vararg keys: Char,
) {
    /** Keys: **N**orth, **U**p */
    NORTH(0, -1, { SOUTH }, { EAST }, 'N', 'U', '^'),

    /** Keys: **E**ast, **R**ight */
    EAST(1, 0, { WEST }, { SOUTH }, 'E', 'R', '>'),

    /** Keys: **S**outh, **D**own */
    SOUTH(0, 1, { NORTH }, { WEST }, 'S', 'D', 'v'),

    /** Keys: **W**est, **L**eft */
    WEST(-1, 0, { EAST }, { NORTH }, 'W', 'L', '<'),

    NORTH_EAST(1, -1, { SOUTH_WEST }, { SOUTH_EAST }),
    SOUTH_EAST(1, 1, { NORTH_WEST }, { SOUTH_WEST }),
    SOUTH_WEST(-1, 1, { NORTH_EAST }, { NORTH_WEST }),
    NORTH_WEST(-1, -1, { SOUTH_EAST }, { NORTH_EAST });

    val keys = keys.toTypedArray()

    val xl by lazy(x::toLong)
    val yl by lazy(y::toLong)

    val vec by lazy { x by y }
    val vecl by lazy { xl by yl }

    val opposite by lazy(opposite)
    val perpendicular by lazy { perpendicular().let { arrayOf(it, it.opposite) } }

    operator fun times(other: Int) = vec * other
    operator fun times(other: Long) = vecl * other

    companion object {
        val UP = NORTH
        val DOWN = SOUTH
        val LEFT = WEST
        val RIGHT = EAST

        fun noDiag(direction: Direction): Nothing = throw RuntimeException("Unexpected diagonal $direction")
        fun noDir(direction: Direction): Nothing = throw RuntimeException("Unexpected direction $direction")

        fun all(start: Int = 0) = DirectionContainer(start, *entries.toTypedArray())
        fun straight(start: Int = 0) = DirectionContainer(start, NORTH, EAST, SOUTH, WEST)
        fun diagonal(start: Int = 0) = DirectionContainer(start, NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST)

        fun of(vararg directions: Direction): DirectionContainer {
            if (directions.isEmpty()) throw IllegalArgumentException("Direction container cannot be empty")
            return DirectionContainer(start = 0, directions = directions)
        }

        operator fun invoke(char: Char) = entries.first { char in it.keys }
        operator fun invoke(string: String) = entries.first { string[0] in it.keys }

        operator fun Vector2i.plus(direction: Direction) = this + direction.vec
        operator fun Vector2i.plus(container: DirectionContainer) = this + container.current

        operator fun Vector2l.plus(direction: Direction) = this + direction.vecl
        operator fun Vector2l.plus(container: DirectionContainer) = this + container.current
    }

    class DirectionContainer internal constructor(start: Int, vararg directions: Direction) : Iterable<Direction> {

        private val entries = arrayOf(*directions)
        private var index = start

        val size = directions.size
        val current get() = entries[index]

        fun next() {
            index = (index + 1).mod(size)
        }

        fun next(direction: Direction): Direction {
            val index = entries.indexOf(direction)
            return entries[(index + 1).mod(size)]
        }

        fun previous(direction: Direction): Direction {
            val index = entries.indexOf(direction)
            return entries[(index - 1).mod(size)]
        }

        operator fun invoke(index: Int) = entries[index.mod(entries.size)]

        operator fun times(other: Int) = current * other
        operator fun times(other: Long) = current * other

        override fun iterator() = entries.iterator()
    }
}