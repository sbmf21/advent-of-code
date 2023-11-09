package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.math.Vector2i

class Day17(input: List<String>) : ADay(input) {

    private val jet = input[0]

    override fun part1(): Any {

        val tetris = Tetris()
        var rock = tetris.next()
        var rockCount = 0
        var index = 0

        while (rockCount < 2022) {
            when (jet[index++]) {
                '<' -> rock.moveLeft()
                '>' -> rock.moveRight()
            }
            index %= jet.length

            if (!rock.moveDown()) {
                rock.place()
                rock = tetris.next()
                rockCount++
            }
        }

        return tetris.maxOf { it.y } + 1
    }

    override fun part2(): Any {
        return -1
    }
}

private class Tetris : MutableList<Vector2i> by mutableListOf() {
    private var rockIndex = 0
    private val rocks = listOf(
        { listOf(Vector2i(0, 0), Vector2i(1, 0), Vector2i(2, 0), Vector2i(3, 0)) },
        { listOf(Vector2i(1, 0), Vector2i(0, 1), Vector2i(1, 1), Vector2i(2, 1), Vector2i(1, 2)) },
        { listOf(Vector2i(2, 2), Vector2i(2, 1), Vector2i(0, 0), Vector2i(1, 0), Vector2i(2, 0)) },
        { listOf(Vector2i(0, 0), Vector2i(0, 1), Vector2i(0, 2), Vector2i(0, 3)) },
        { listOf(Vector2i(0, 0), Vector2i(1, 0), Vector2i(0, 1), Vector2i(1, 1)) },
    )

    fun next(): Rock {
        val rock = rocks[rockIndex++]
        rockIndex %= rocks.size
        return Rock(this, rock.invoke(), Vector2i(2, (maxOfOrNull { it.y } ?: -1) + 4))
    }
}

private class Rock(
    val tetris: Tetris,
    val data: List<Vector2i>,
    position: Vector2i,
) {
    init {
        data.forEach { it += position }
    }

    fun moveLeft() {
        if (data.map { it - Vector2i(1, 0) }.any { it.x < 0 || it in tetris }) return
        data.forEach { it -= Vector2i(1, 0) }
    }

    fun moveRight() {
        if (data.map { it + Vector2i(1, 0) }.any { it.x > 6 || it in tetris }) return
        data.forEach { it += Vector2i(1, 0) }
    }

    fun moveDown(): Boolean {
        if (data.map { it - Vector2i(0, 1) }.any { it in tetris || it.y < 0 }) return false
        data.forEach { it -= Vector2i(0, 1) }
        return true
    }

    fun place() {
        tetris.addAll(data)
    }
}