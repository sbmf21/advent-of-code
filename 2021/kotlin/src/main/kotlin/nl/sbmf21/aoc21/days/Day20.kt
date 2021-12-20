package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay

class Day20(input: List<String>) : ADay(input) {

    private val algorithm = input[0].map { if (it == '#') 1 else 0 }
    private val image = input.subList(2, input.size).map { r -> r.map { if (it == '#') 1 else 0 } }

    override fun part1() = run(2)
    override fun part2() = run(50)

    private fun run(times: Int) = (1..times).fold(image) { image, i ->
        List(image.size + 2) { y -> MutableList(image.size + 2) { x -> check(y - 1, x - 1, image, i) } }
    }.sumOf { r -> r.count { it == 1 } }

    private fun check(y: Int, x: Int, image: List<List<Int>>, pass: Int) = algorithm[(y - 1..y + 1).map { cy ->
        (x - 1..x + 1).map { cx ->
            if (cy in image.indices && cx in image[cy].indices) image[cy][cx]
            else algorithm[if (algorithm[0] == 1) pass % 2 * algorithm.lastIndex else 0]
        }
    }.flatten().joinToString("").toInt(2)]
}
