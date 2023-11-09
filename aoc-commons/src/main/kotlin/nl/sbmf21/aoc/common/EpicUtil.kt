package nl.sbmf21.aoc.common

inline fun <T> MutableList<T>.iterated(action: (MutableIterator<T>, T) -> Unit): MutableList<T> {
    val iterator = iterator()

    while (iterator.hasNext()) action(iterator, iterator.next())

    return this
}

fun List<String>.mapToInts() = map { it.toInt() }
fun List<String>.mapToLongs() = map { it.toLong() }
fun List<String>.mapToFloats() = map { it.toFloat() }
fun List<String>.mapToDoubles() = map { it.toDouble() }
fun List<Int>.prod() = fold(1) { acc, i -> acc * i }

fun <E> List<E>.subList(fromIndex: Int) = subList(fromIndex, size)