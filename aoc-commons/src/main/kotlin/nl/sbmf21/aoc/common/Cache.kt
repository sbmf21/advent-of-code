package nl.sbmf21.aoc.common

private typealias DayCache = Pair<Int, List<String>>

internal object Cache {

    private val backing = mutableMapOf<Class<out ADay>, DayCache>()

    operator fun set(clazz: Class<out ADay>, data: DayCache) {
        backing[clazz] = data
    }

    infix fun unset(clazz: Class<out ADay>) {
        backing.remove(clazz)
    }

    operator fun get(day: Class<out ADay>): DayCache {
        return backing[day]!!
    }
}