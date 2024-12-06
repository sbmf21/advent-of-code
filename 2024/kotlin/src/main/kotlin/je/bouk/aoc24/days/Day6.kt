package je.bouk.aoc24.days

import je.bouk.aoc24.days.Day6.Direction.entries
import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.TODO
import nl.sbmf21.aoc.common.grid
import nl.sbmf21.math.Vector2i
import nl.sbmf21.math.by

class Day6 : Day() {

    val grid = input.grid { _, _, c -> Tile(c) }

    override fun part1(): Any {
        val start = grid.entries.first { it.value.start }.key

        val pos = buildSet {
            add(start)

            var d = Direction.UP
            var pos = start

            do {
                val next = pos + d.vec
                val tile = grid[next] ?: break

                if (tile.obstacle) {
                    d = d.next()
                } else {
                    add(next)
                    pos = next
                }
            } while (pos in grid)
        }

        return pos.size
    }

    override fun part2(): Any {
        return TODO
//        val start = grid.entries.first { it.value.start }.key
//
//        val loops = mutableSetOf<Vector2i>()
//
//        val pos = buildMap<Vector2i, MutableList<Direction>> {
//
//            var d = Direction.UP
//            var pos = start
//
//            this[pos] = mutableListOf(d)
//
//            fun add() {
//                if (pos in this) {
//                    this[pos]!!.add(d)
//                } else this[pos] = mutableListOf(d)
//            }
//
//            do {
//                val next = pos + d.vec
//                val tile = grid[next] ?: break
//
//                if (tile.obstacle) {
//                    d = d.next()
//
//                    add()
//                } else {
//                    add()
//
//                    val dr = d.next()
//                    var pr = next + dr.vec
//
//                    while (pr in grid) {
//                        if (grid[pr]!!.obstacle) break
//                        if (pr in this && (dr in  this[pr]!! || (grid[pr+dr.vec]?.obstacle == true && dr.next() in (this[pr+dr.next().vec]?:listOf()) ))) {
//                            loops.add(next + d.vec)
//                            break
//                        }
//                        pr = pr + dr.vec
//                    }
//
//                    pos = next
//                }
//            } while (pos in grid)
//        }
//
//        val mx = grid.maxOf { it.key.x }
//        val my = grid.maxOf { it.key.y }
//
//        for (y in 0..my) {
//            for (x in 0..mx) {
//                val p = x by y
//                val tile = grid[p]!!
//                if (tile.obstacle) print('#')
//                else if (p in loops) print('O')
//                else if (p in pos) {
//                    val ds = pos[p]!!
//                    if (ds.size > 1) print('+')
//                    else if (ds[0] == Direction.LEFT || ds[0] == Direction.RIGHT) print('-')
//                    else print('|')
//                } else print(' ')
//            }
//            println()
//        }
//
//
//
//        return loops.size
    }

    class Tile(val char: Char) {
        val start get() = char == '^'
        val obstacle = char == '#'
    }

    enum class Direction(x: Int, y: Int) {
        UP(0, -1),
        RIGHT(1, 0),
        DOWN(0, 1),
        LEFT(-1, 0);

        val vec = x by y

        fun next() = entries[(ordinal + 1) % entries.size]
    }
}