package nl.sbmf21.aoc.common

import java.util.Collections.synchronizedMap

private typealias DayCache = Pair<Int, List<String>>

internal object Cache {

    private val backing = synchronizedMap(mutableMapOf<Class<out Day>, DayCache>())

    operator fun set(clazz: Class<out Day>, data: DayCache) {
        backing[clazz] = data
    }

    operator fun get(clazz: Class<out Day>): DayCache {
        return backing[clazz]!!
    }
}