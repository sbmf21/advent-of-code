package nl.sbmf21.aoc.common

private typealias DayCache = Pair<Int, List<String>>

internal object Cache {

    private val backing = mutableMapOf<Class<out Day>, DayCache>()

    operator fun set(clazz: Class<out Day>, data: DayCache) {
        backing[clazz] = data
    }

    infix fun unset(clazz: Class<out Day>) {
        backing.remove(clazz)
    }

    operator fun get(clazz: Class<out Day>): DayCache {
        return backing[clazz]!!
    }
}