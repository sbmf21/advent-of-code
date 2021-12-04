package nl.sbmf21.aoc.common

inline fun <T> MutableList<T>.iterated(action: (MutableIterator<T>, T) -> Unit): MutableList<T> {
    val iterator = iterator()

    while (iterator.hasNext()) action(iterator, iterator.next())

    return this
}
