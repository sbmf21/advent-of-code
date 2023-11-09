package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.math.Vector2i
import nl.sbmf21.math.clamp

class Day9(input: List<String>) : ADay(input) {

    var store: ((frame: Int, index: Int, pos: Vector2i) -> Unit)? = null
    private val actions = input
        .map { it.split(" ") }
        .map { RopeAction(RopeDirection.from(it[0]), it[1].toInt()) }
    private val valid = listOf(
        Vector2i(-1, 1), Vector2i(0, 1), Vector2i(1, 1),
        Vector2i(-1, 0), Vector2i(0, 0), Vector2i(1, 0),
        Vector2i(-1, -1), Vector2i(0, -1), Vector2i(1, -1),
    )

    override fun part1() = run(1)

    override fun part2() = run()

    fun run(knotCount: Int = 9): Int {
        val head = Vector2i()
        val knots = List(knotCount) { Vector2i() }
        val visited = mutableSetOf(Vector2i())
        var frame = 0
        store?.invoke(frame, 0, Vector2i())

        actions.forEach {
            (1..it.distance).forEach { _ ->
                frame++
                head += it.direction.vec
                store?.invoke(frame, 0, Vector2i(head.x, head.y))
                var prev = head
                knots.forEachIndexed { i, knot ->
                    val diff = prev - knot
                    if (diff !in valid) {
                        knot += Vector2i(clamp(diff.x, -1, 1), clamp(diff.y, -1, 1))
                        if (i == knotCount - 1) visited.add(Vector2i(knot.x, knot.y))
                    }
                    store?.invoke(frame, i + 1, Vector2i(knot.x, knot.y))
                    prev = knot
                }
            }
        }

        return visited.size
    }
}

private data class RopeAction(val direction: RopeDirection, val distance: Int)

private enum class RopeDirection(val string: String, val vec: Vector2i) {
    UP("U", Vector2i(0, 1)),
    DOWN("D", Vector2i(0, -1)),
    LEFT("L", Vector2i(-1, 0)),
    RIGHT("R", Vector2i(1, 0));

    companion object {
        fun from(string: String) = entries.first { it.string == string }
    }
}