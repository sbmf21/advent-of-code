package nl.sbmf21.aoc.common

import nl.sbmf21.math.Vector2i
import nl.sbmf21.math.by

typealias GridValueBuilder<T> = (Int, Int, Char) -> T

fun String.grid() = lines().grid()

fun List<String>.grid(): List<Vector2i> = buildList {
    for (y in indices) for (x in this@grid[y].indices) {
        this += x by y
    }
}

fun <T> String.grid(value: GridValueBuilder<T>) = lines().grid(value)

fun <T> List<String>.grid(value: GridValueBuilder<T>): Map<Vector2i, T> = buildMap {
    for (y in indices) for (x in this@grid[y].indices) {
        this[x by y] = value(x, y, this@grid[y][x])
    }
}