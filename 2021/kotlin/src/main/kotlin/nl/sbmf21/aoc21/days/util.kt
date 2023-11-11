package nl.sbmf21.aoc21.days

internal fun MatchResult.toInt(name: String) = this.groups[name]!!.value.toInt()
internal fun MatchResult.toLong(name: String) = this.groups[name]!!.value.toLong()
internal inline fun <reified T> List<T>.aTake(n: Int): Array<T> = take(n).toTypedArray()
internal inline fun <reified T> List<T>.aTakeLast(n: Int): Array<T> = takeLast(n).toTypedArray()