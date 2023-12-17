package nl.sbmf21.aoc.common

inline fun <T> MutableList<T>.iterated(action: (MutableIterator<T>, T) -> Unit): MutableList<T> {
    val iterator = iterator()

    while (iterator.hasNext()) action(iterator, iterator.next())

    return this
}

inline fun <reified T : Puzzle, reified S : Simulation<T>> simulate(crossinline build: (T) -> S): (AocBase) -> S = {
    val day = it.days.first { meta -> meta.clazz == T::class.java }.build() as T
    println("Simulating day ${day.number}")
    build.invoke(day)
}

fun <T> List<T>.subList(fromIndex: Int) = subList(fromIndex, size)

fun <T> Iterable<T>.split(ignoreEmpty: Boolean = true, predicate: (T) -> Boolean) = buildList {
    val buffer = mutableListOf<T>()

    this@split.forEach {
        if (predicate(it)) {
            if (!ignoreEmpty || buffer.isNotEmpty()) {
                add(buffer.toList())
                buffer.clear()
            }
        } else {
            buffer.add(it)
        }
    }

    if (buffer.isNotEmpty()) {
        add(buffer.toList())
    }
}

fun <K, V, R> Map<K, V>.map(transform: (K, V) -> R) = map { (k, v) -> transform(k, v) }

fun List<String>.transpose() = List(get(0).length) { idx -> joinToString("") { "${it[idx]}" } }

fun CharSequence.splitToLongs(vararg delimiters: String, ignoreCase: Boolean = false) = this
    .split(delimiters = delimiters, ignoreCase = ignoreCase)
    .mapToLongs()

fun CharSequence.splitToInts(vararg delimiters: String, ignoreCase: Boolean = false) = this
    .split(delimiters = delimiters, ignoreCase = ignoreCase)
    .mapToInts()

fun List<String>.mapToInts() = map(String::toInt)

fun List<String>.mapToLongs() = map(String::toLong)

fun List<String>.mapToFloats() = map(String::toFloat)

fun List<String>.mapToDoubles() = map(String::toDouble)

fun List<Int>.prod() = fold(1) { acc, i -> acc * i }

val String.llength get() = length.toLong()