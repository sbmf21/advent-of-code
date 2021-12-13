package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc.common.AOC_LETTERS
import nl.sbmf21.aoc.common.mapToInts

class Day13(input: List<String>) : ADay(input) {

    private val pattern = Regex("\\d+,\\d+")
    private val dots = input.filter { it.matches(pattern) }.map { it.split(',').mapToInts() }.map { Pair(it[0], it[1]) }

    override fun part1() = run(true).run { paper.subList(0, height).sumOf { it.subList(0, width).count { p -> p } } }

    override fun part2() = run().also { if (it.width != 40 || it.height != 6) return "nil" }.run {
        (0 until 8).fold("") { code, i ->
            val letter = List(6) { MutableList(4) { 0 } }
            for (y in 0 until 6) for (x in 0 until 4) letter[y][x] = if (paper[y][i * 5 + x]) 1 else 0
            code + (AOC_LETTERS.firstOrNull { it.second == letter }?.first ?: "")
        }
    }

    private fun run(stopAtFirst: Boolean = false): Scrabble {
        var width = dots.maxOf { it.first }; var height = dots.maxOf { it.second }
        val paper = map(width + 1, height + 1)

        input.filter { it.startsWith("fold along ") }.map { it.removePrefix("fold along ").split('=') }.forEach {
            val fold = it[1].toInt()
            if (it[0] == "x") foldX(paper, width, height, fold).also { width = fold }
            else foldY(paper, width, height, fold).also { height = fold }
            if (stopAtFirst) return Scrabble(paper, width + 1, height + 1)
        }

        return Scrabble(paper, width, height)
    }

    private fun foldX(map: List<MutableList<Boolean>>, width: Int, height: Int, fold: Int) {
        for (x in fold..width) for (y in 0..height) if (map[y][x]) map[y][fold - (x - fold)] = true
    }

    private fun foldY(map: List<MutableList<Boolean>>, width: Int, height: Int, fold: Int) {
        for (y in fold..height) for (x in 0..width) if (map[y][x]) map[fold - (y - fold)][x] = true
    }

    private fun map(width: Int, height: Int) = dots.fold(List(height) { MutableList(width) { false } }) { m, d ->
        m[d.second][d.first] = true; m
    }
}

internal data class Scrabble(val paper: List<MutableList<Boolean>>, val width: Int, val height: Int)
