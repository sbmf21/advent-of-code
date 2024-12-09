package je.bouk.aoc24.days

import nl.sbmf21.aoc.common.Day

/**
 * Yes, this is slow and the code is ugly, but this took me a couple of hours, so I will clean it up later.
 */
class Day9 : Day() {

    private companion object {
        val empty = Char(Short.MAX_VALUE.toInt())
    }

    private val lastId: Int
    private val diskMap: String = input[0]
    private val layout: String

    init {
        var id = 0
        layout = diskMap.indices.joinToString("") {
            (if (it % 2 == 0) Char(id++) else empty).toString().repeat("${diskMap[it]}".toInt())
        }
        lastId = id
    }

    override fun part1(): Any {

        val len = layout.count { it == empty }
        val max = layout.length - len
        var i1 = 0

        var out = layout.substring(0, max)

        while (out.contains(empty)) {
            var ri = out.indexOfFirst { it == empty }
            val rip1 = ri + 1

            out = if (rip1 in out.indices) {
                out.substring(0, ri) + layout[layout.lastIndex - i1] + out.substring(rip1)
            } else {
                out.substring(0, ri) + layout[layout.lastIndex - i1]
            }

            i1++
        }


        return out.checksum()
    }

    override fun part2(): Any {

        var todo = lastId - 1

        // mapping chars
        val segments = layout.fold(mutableListOf<Segment>()) { list, c ->
            if (list.isEmpty()) {
                list += Segment(c, 1)
            } else if (list.last().id == c) {
                list.last().length++
            } else {
                list += Segment(c, 1)
            }

            list
        }

        while (todo >= 0) {
            val file = segments.last { it.id.code == todo }
            val empty = segments
                .subList(0, segments.indexOf(file)) // only look for empty blocks in front of the current file
                .firstOrNull { it.id == empty && it.length >= file.length }

            if (empty != null) {
                // add new empty segment, we won't move anything here so it doesn't matter that there are consecutive
                // segments with the same empty block data, but it's important to keep track if block indices
                segments.add(segments.indexOf(file), Segment(Companion.empty, file.length))
                segments.remove(file)

                // remove segment length from empty segment length, to keep track of block indices
                empty.length -= file.length
                segments.add(segments.indexOf(empty), file)
            }

            todo--
        }

        return segments.joinToString("") { "${it.id}".repeat(it.length) }.checksum()
    }

    private class Segment(val id: Char, var length: Int)

    private fun String.checksum() = withIndex()
        .filterNot { (_, c) -> c == empty }
        .sumOf { (index, char) -> index * char.code.toLong() }
}