package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay

internal typealias OctoMap = List<MutableList<Int>>
internal typealias FlashMap = List<MutableList<Boolean>>

class Day11(input: List<String>) : ADay(input) {

    override fun part1(): Any {
        val octi = mapOctopodes(); var count = 0
        for (s in 1..100) step(octi) { count += it.sumOf { r -> r.count { f -> f } } }
        return count
    }

    override fun part2(): Any {
        val grid = mapOctopodes(); var step = 0
        while (true) step(grid) { step++; if (it.sumOf { r -> r.count { f -> !f } } == 0) return step }
    }

    private inline fun step(octi: OctoMap, finish: (a: FlashMap) -> Unit) {
        val flashed = mapFlashers(octi)

        for (y in octi.indices) for (x in octi[y].indices) octi[y][x]++
        for (y in octi.indices) for (x in octi[y].indices) if (octi[y][x] > 9) flash(y, x, octi, flashed)
        for (y in flashed.indices) for (x in flashed[y].indices) if (flashed[y][x]) octi[y][x] = 0
        finish(flashed)
    }

    private fun flash(y: Int, x: Int, octi: OctoMap, flashed: FlashMap) {
        if (flashed[y][x]) return; flashed[y][x] = true
        for (ny in y - 1..y + 1) for (nx in x - 1..x + 1) if (ny in octi.indices && nx in octi[ny].indices) {
            if (ny == y && nx == x) continue
            if (++octi[ny][nx] > 9) flash(ny, nx, octi, flashed)
        }
    }

    private fun mapOctopodes() = input.map { r -> r.toCharArray().map { it.toString().toInt() }.toMutableList() }
    private fun mapFlashers(octi: OctoMap): FlashMap = octi.map { it.map { false }.toMutableList() }
}
