package nl.sbmf21.aoc.common

import java.util.Collections.synchronizedMap

private typealias PuzzleCache = Pair<Int, List<String>>

internal object Cache {

    private val backing = synchronizedMap(mutableMapOf<Class<out Puzzle>, PuzzleCache>())

    operator fun set(clazz: Class<out Puzzle>, data: PuzzleCache) {
        backing[clazz] = data
    }

    operator fun get(clazz: Class<out Puzzle>): PuzzleCache {
        return backing[clazz]!!
    }
}