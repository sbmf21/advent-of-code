package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.util.Direction
import nl.sbmf21.aoc.common.util.Direction.Companion.plus
import nl.sbmf21.math.Vector2i

class Day24 : Day() {

    private companion object {
        const val GROUND = '.'

        val directions = Direction.straight()

        fun blizzard(day: Day24, x: Int, y: Int, char: Char): Blizzard? = directions
            .firstOrNull { char in it.keys }
            ?.run { day.Blizzard(Vector2i(x, y), this) }
    }

    private val initial = Valley(input.flatMapIndexed { y, row ->
        row.mapIndexedNotNull { x, char -> blizzard(this, x, y, char) }
    })
    private val start = Vector2i(input[0].indexOf(GROUND), 0)
    private val end = Vector2i(input.last().indexOf(GROUND), input.lastIndex)
    private val maxX = input[0].lastIndex - 1
    private val maxY = input.lastIndex - 1

    override fun part1() = navigate().time

    override fun part2() = navigate()
        .run { navigate(this, end, start) }
        .run { navigate(this) }
        .time

    private fun navigate(
        initial: Valley = this.initial,
        start: Vector2i = this.start,
        end: Vector2i = this.end,
    ): Valley {
        var valley = initial
        var steps = setOf(start)

        while (true) {
            valley = valley.blow()
            steps = buildSet {
                steps.forEach { current ->
                    if (valley.canMove(current) || current == start) add(current)
                    directions.map { current + it }
                        .onEach { if (it == end) return valley }
                        .filter(valley::canMove)
                        .also(::addAll)
                }
            }
        }
    }

    private inner class Valley(val blizzards: List<Blizzard>, val time: Int = 0) {
        private val blizzardPositions = buildSet { blizzards.forEach { add(it.pos) } }

        fun blow() = Valley(blizzards.map(Blizzard::blow), time + 1)

        fun canMove(pos: Vector2i) = pos !in blizzardPositions
            && pos.x in 1..maxX
            && pos.y in 1..maxY
    }

    private inner class Blizzard(val pos: Vector2i, val direction: Direction) {
        fun blow() = (pos + direction).run {
            Blizzard(
                when {
                    x < 1 -> Vector2i(maxX, y)
                    y < 1 -> Vector2i(x, maxY)
                    x > maxX -> Vector2i(1, y)
                    y > maxY -> Vector2i(x, 1)
                    else -> this
                },
                direction,
            )
        }
    }
}