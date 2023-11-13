package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc.common.by
import nl.sbmf21.aoc22.days.Day22.CubeEdge.*
import nl.sbmf21.aoc22.days.Day22.CubeFace.*
import nl.sbmf21.aoc22.days.Day22.Direction.*
import nl.sbmf21.aoc22.days.Day22.Direction.Companion.plus
import nl.sbmf21.aoc22.days.Day22.Rotation.*
import nl.sbmf21.math.Vector2i
import kotlin.math.abs

class Day22(input: List<String>) : ADay(input) {

    private val regex = Regex("(\\d+)([A-Z])?")
    private val actions = buildList {
        regex.findAll(input.last()).map(MatchResult::groupValues).forEach { match ->
            add(MoveAction(match[1].toInt()))
            if (match[2].isNotEmpty()) add(Rotation(match[2][0]))
        }
    }
    private val mapData = input.take(input.size - 2)

    override fun part1() = followMap(Board(buildMap {
        mapData.forEachIndexed { y, line ->
            line.withIndex().filterNot { it.value == ' ' }.forEach { (x, c) ->
                this[x + 1 by y + 1] = c == '.'
            }
        }
    }))

    override fun part2() = followMap(Cube(buildList {
        val size = mapData.maxOf(String::length).toFloat().let { abs(it * (1 - mapData.size / it)).toInt() }
        mapData.chunked(size).forEachIndexed { chunkY, chunk ->
            for (chunkX in 0 until chunk[0].length / size) {
                val chunkXStart = chunkX * size
                if (chunk[0][chunkXStart] == ' ') continue
                add(Face(chunkX by chunkY, size, buildMap {
                    for (y in 0 until size) {
                        for (x in 0 until size) {
                            this[x + 1 by y + 1] = chunk[y][chunkXStart + x] == '.'
                        }
                    }
                }))
            }
        }
    }))

    private fun followMap(map: MonkeyMap): Int {
        actions.forEach(map::move)
        return 1000 * map.finalPosition.y + 4 * map.finalPosition.x + map.direction.ordinal
    }

    private sealed interface MonkeyMap {
        val finalPosition: Vector2i get() = position
        val position: Vector2i
        val direction: Direction

        fun move(action: Action)
    }

    private data class Board(val tiles: Map<Vector2i, Boolean>) : MonkeyMap {

        override var position = tiles.keys.filter { it.y == 1 }.minBy { it.x }
            private set
        override var direction = EAST
            private set

        override fun move(action: Action) = when (action) {
            is Rotation -> direction = action.rotate(direction)
            is MoveAction -> repeat(action.moves) {
                (position + direction).run {
                    if (this in tiles) this
                    else tiles.keys.run {
                        when (direction) {
                            WEST -> filter { it.y == y }.maxBy { it.x }
                            EAST -> filter { it.y == y }.minBy { it.x }
                            SOUTH -> filter { it.x == x }.minBy { it.y }
                            NORTH -> filter { it.x == x }.maxBy { it.y }
                        }
                    }
                }.let { if (tiles[it]!!) position = it }
            }
        }
    }

    private inner class Cube(private val faces: List<Face>) : MonkeyMap {

        override val finalPosition get() = position + face.start
        override var position = faces.first().tiles.minBy { (pos, _) -> pos.x + pos.y }.key
            private set
        override var direction = EAST
            private set

        private val dimension = faces.first().let { it.end.x - it.start.x }
        private var face = faces[0]
        private val edges = buildList {
            val connected = faces.associateWith { face ->
                buildMap {
                    Direction.entries.map { face.coord + it to it }.forEach { (coord, direction) ->
                        faces.firstOrNull { otherFace -> otherFace.coord == coord }?.let { this[direction] = it }
                    }
                }
            }

            val unfinished = mutableMapOf<CubeEdge, Pair<Face, Direction>>()
            val seen = mutableSetOf(face)
            val batch = mutableListOf(Triple(TOP, face, NONE))

            while (batch.isNotEmpty()) {
                val (cubeFace, face, rotation) = batch.removeFirst()
                Direction.entries.forEach { direction ->
                    val edge = cubeFace.edges[rotation.rotate(direction)]!!
                    val other = connected[face]!![direction]
                    if (other != null && other !in seen) {
                        val otherEdge = edge.connected(cubeFace)
                        batch += Triple(otherEdge, other, Rotation(otherEdge.edgeDirection(edge), direction.opposite))
                        seen += other
                    }

                    if (edge in unfinished) add(Edge(unfinished[edge]!!, face to direction))
                    else unfinished[edge] = face to direction
                }
            }
        }

        override fun move(action: Action) = when (action) {
            is Rotation -> direction = action.rotate(direction)
            is MoveAction -> repeat(action.moves) {
                (position + direction).let {
                    if (it.x in 1..dimension && it.y in 1..dimension)
                        Triple(face, it, direction)
                    else face.edge(direction)
                        .run { edges.first { (l, r) -> l == this || r == this } }
                        .next(face, it, direction)
                }.also { (face, position, direction) ->
                    if (face.tiles[position]!!) {
                        this.face = face
                        this.position = position
                        this.direction = direction
                    }
                }
            }
        }
    }

    private data class Face(val coord: Vector2i, val size: Int, val tiles: Map<Vector2i, Boolean>) {
        private val edges = Direction.entries.associateWith { this to it }
        val start = coord * size
        val end = start + size

        fun edge(direction: Direction) = edges[direction]!!
    }

    private data class Edge(val left: Pair<Face, Direction>, val right: Pair<Face, Direction>) {
        fun next(face: Face, position: Vector2i, direction: Direction): Triple<Face, Vector2i, Direction> {
            val (previous, new) = if (face == right.first) right to left else left to right
            val max = new.first.size
            val exit = when (direction) {
                NORTH, SOUTH -> position.x
                WEST, EAST -> position.y
            }
            val entry = max - exit + 1

            // @formatter:off
            return Triple(new.first, when (new.second) {
                NORTH -> when (previous.second) { NORTH, EAST -> entry; SOUTH, WEST -> exit } by 1
                SOUTH -> when (previous.second) { NORTH, EAST -> exit; SOUTH, WEST -> entry } by max
                WEST -> 1 by when (previous.second) { NORTH, EAST -> exit; SOUTH, WEST -> entry }
                EAST -> max by when (previous.second) { NORTH, EAST -> entry; SOUTH, WEST -> exit }
            }, new.second.opposite)
            // @formatter:on
        }
    }

    private sealed interface Action

    private data class MoveAction(val moves: Int) : Action

    private enum class CubeFace(edges: () -> Map<Direction, CubeEdge>) {
        TOP({ mapOf(NORTH to TOP_BACK, EAST to TOP_RIGHT, SOUTH to TOP_FRONT, WEST to TOP_LEFT) }),
        BOTTOM({ mapOf(NORTH to BOTTOM_FRONT, EAST to BOTTOM_RIGHT, SOUTH to BOTTOM_BACK, WEST to BOTTOM_LEFT) }),
        FRONT({ mapOf(NORTH to TOP_FRONT, EAST to RIGHT_FRONT, SOUTH to BOTTOM_FRONT, WEST to LEFT_FRONT) }),
        BACK({ mapOf(NORTH to TOP_BACK, EAST to LEFT_BACK, SOUTH to BOTTOM_BACK, WEST to RIGHT_BACK) }),
        RIGHT({ mapOf(NORTH to TOP_RIGHT, EAST to RIGHT_BACK, SOUTH to BOTTOM_RIGHT, WEST to RIGHT_FRONT) }),
        LEFT({ mapOf(NORTH to TOP_LEFT, EAST to LEFT_FRONT, SOUTH to BOTTOM_LEFT, WEST to LEFT_BACK) });

        val edges by lazy(edges)

        fun edgeDirection(edge: CubeEdge) = edges.entries.first { it.value == edge }.key
    }

    private enum class CubeEdge(faces: () -> Pair<CubeFace, CubeFace>) {
        TOP_FRONT({ TOP to FRONT }),
        TOP_BACK({ TOP to BACK }),
        TOP_LEFT({ TOP to LEFT }),
        TOP_RIGHT({ TOP to RIGHT }),
        BOTTOM_FRONT({ BOTTOM to FRONT }),
        BOTTOM_BACK({ BOTTOM to BACK }),
        BOTTOM_LEFT({ BOTTOM to LEFT }),
        BOTTOM_RIGHT({ BOTTOM to RIGHT }),
        LEFT_FRONT({ LEFT to FRONT }),
        LEFT_BACK({ LEFT to BACK }),
        RIGHT_FRONT({ RIGHT to FRONT }),
        RIGHT_BACK({ RIGHT to BACK });

        private val faces by lazy(faces)

        fun connected(face: CubeFace) = if (faces.first == face) faces.second else faces.first
    }

    private enum class Rotation(private val rotate: (Int) -> Int) : Action {
        COUNTER_CLOCKWISE({ it + 3 }),
        CLOCKWISE({ it + 1 }),
        MIRROR({ it + 2 }),
        NONE({ it });

        fun rotate(direction: Direction) = Direction(rotate(direction.ordinal))

        companion object {
            operator fun invoke(from: Direction, to: Direction) = Direction(from.ordinal - to.ordinal).rotation

            operator fun invoke(from: Char) = when (from) {
                'L' -> COUNTER_CLOCKWISE
                'R' -> CLOCKWISE
                else -> error("Bad rotation: $from")
            }
        }
    }

    private enum class Direction(private val vector: Vector2i, opposite: () -> Direction, rotation: () -> Rotation) {
        EAST(1 by 0, { WEST }, { NONE }),
        SOUTH(0 by 1, { NORTH }, { CLOCKWISE }),
        WEST(-1 by 0, { EAST }, { MIRROR }),
        NORTH(0 by -1, { SOUTH }, { COUNTER_CLOCKWISE });

        val opposite by lazy(opposite)
        val rotation by lazy(rotation)

        companion object {
            operator fun invoke(index: Int) = entries[index.mod(entries.size)]
            operator fun Vector2i.plus(direction: Direction) = plus(direction.vector)
        }
    }
}