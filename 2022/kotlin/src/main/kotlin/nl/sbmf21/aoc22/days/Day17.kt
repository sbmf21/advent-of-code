package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.math.Vector2l
import nl.sbmf21.math.by

class Day17 : Day() {

    private companion object {
        val left = Vector2l(-1, 0)
        val right = Vector2l(1, 0)
        val down = Vector2l(0, -1)

        val rocks = listOf(
            listOf(2L by 3L, 3L by 3L, 4L by 3L, 5L by 3L),
            listOf(3L by 5L, 2L by 4L, 3L by 4L, 4L by 4L, 3L by 3L),
            listOf(4L by 5L, 4L by 4L, 2L by 3L, 3L by 3L, 4L by 3L),
            listOf(2L by 6L, 2L by 5L, 2L by 4L, 2L by 3L),
            listOf(2L by 4L, 3L by 4L, 2L by 3L, 3L by 3L),
        )
    }

    private val jet = input[0]

    override fun part1() = drop(2022)
    override fun part2() = drop(1_000_000_000_000)

    private fun drop(maxRocks: Long): Long {
        val tetris = Tetris()
        val seen = hashMapOf<State, Pair<Long, Long>>()

        while (tetris.rockCount < maxRocks) {
            val state = tetris.state()

            seen[state]?.let { (oldRockCount, oldHeight) ->
                val cyclePeriod = tetris.rockCount - oldRockCount
                if (tetris.rockCount % cyclePeriod == maxRocks % cyclePeriod) {
                    return oldHeight + (tetris.height - oldHeight) * ((maxRocks - tetris.rockCount) / cyclePeriod + 1)
                }
            }
            seen[state] = tetris.rockCount to tetris.height

            val rock = tetris.nextRock()
            while (true) if (!rock.move()) {
                rock.settle()
                break
            }
        }

        return tetris.height
    }

    private data class State(private val jetIndex: Int, private val rockIndex: Int)

    private inner class Tetris : MutableSet<Vector2l> by mutableSetOf() {

        var height: Long = 0
            private set

        var rockCount: Long = 0
            private set

        private var jetIndex: Int = 0
            set(value) {
                field = value % jet.length
            }

        private var rockIndex: Int = 0
            set(value) {
                field = value % rocks.size
            }

        fun state() = State(jetIndex, rockIndex)

        fun nextRock() = Rock(rocks[rockIndex++].map { Vector2l(it.x, it.y + height) })

        private fun nextOperation() = jet[jetIndex++]

        inner class Rock(private val data: List<Vector2l>) {

            fun settle() {
                addAll(data.map { Vector2l(it.x, it.y) })
                height = groupBy { it.y }.count().toLong()
                rockCount++
            }

            fun move(): Boolean {
                when (nextOperation()) {
                    '<' -> moveLeft()
                    '>' -> moveRight()
                }
                return moveDown()
            }

            private fun moveLeft() {
                if (data.map { it + left }.any { it.x < 0 || it in this@Tetris }) return
                data.forEach { it += left }
            }

            private fun moveRight() {
                if (data.map { it + right }.any { it.x > 6 || it in this@Tetris }) return
                data.forEach { it += right }
            }

            private fun moveDown(): Boolean {
                if (data.map { it + down }.any { it.y < 0 || it in this@Tetris }) return false
                data.forEach { it += down }
                return true
            }
        }
    }
}