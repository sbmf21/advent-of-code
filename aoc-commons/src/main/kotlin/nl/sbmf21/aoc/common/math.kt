package nl.sbmf21.aoc.common

fun chineseRemainder(n: List<Long>, a: List<Long>): Long {
    val prod = n.fold(1L) { acc, i -> acc * i }
    var sum = 0L

    for (i in n.indices) {
        val p = prod / n[i]
        sum += a[i] * p.toBigInteger().modInverse(n[i].toBigInteger()).toLong() * p
    }

    return sum.toBigInteger().mod(prod.toBigInteger()).toLong()
}

fun Short.triangular() = (this * (this + 1) / 2).toShort()

fun Int.triangular() = this * (this + 1) / 2

fun Long.triangular() = this * (this + 1) / 2

fun Float.triangular() = this * (this + 1) / 2

fun Double.triangular() = this * (this + 1) / 2

fun UInt.triangular() = this * (this + 1u) / 2u

fun ULong.triangular() = this * (this + 1u) / 2u