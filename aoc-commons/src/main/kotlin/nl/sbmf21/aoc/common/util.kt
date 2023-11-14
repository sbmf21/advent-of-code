package nl.sbmf21.aoc.common

inline fun <T> MutableList<T>.iterated(action: (MutableIterator<T>, T) -> Unit): MutableList<T> {
    val iterator = iterator()

    while (iterator.hasNext()) action(iterator, iterator.next())

    return this
}

inline fun <reified T : Day, reified S : Simulation<T>> simulate(crossinline build: (T) -> S): (AocBase) -> S = {
    val day = it.days.first { meta -> meta.clazz == T::class.java }.build() as T
    println("Simulating day ${day.number}")
    build.invoke(day)
}

fun List<String>.mapToInts() = map(String::toInt)

fun List<String>.mapToLongs() = map(String::toLong)

fun List<String>.mapToFloats() = map(String::toFloat)

fun List<String>.mapToDoubles() = map(String::toDouble)

fun List<Int>.prod() = fold(1) { acc, i -> acc * i }