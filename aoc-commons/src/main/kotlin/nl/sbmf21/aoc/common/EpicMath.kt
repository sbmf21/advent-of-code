package nl.sbmf21.aoc.common

import nl.sbmf21.math.Vector2i
import nl.sbmf21.math.Vector2l

infix fun Int.by(other: Int) = Vector2i(this, other)
infix fun Long.by(other: Long) = Vector2l(this, other)

fun chineseRemainder(n: List<Long>, a: List<Long>): Long {
    val prod = n.fold(1L) { acc, i -> acc * i }
    var sum = 0L

    for (i in n.indices) {
        val p = prod / n[i]
        sum += a[i] * p.toBigInteger().modInverse(n[i].toBigInteger()).toLong() * p
    }

    return sum.toBigInteger().mod(prod.toBigInteger()).toLong()
}

/*
 * This function (although valid syntax) can't compile due to BigInteger and BigDecimal not implementing the correct
 * operator methods.
 */
// fun <N : Number> N.triangular() = this * (this + 1) / 2

fun Short.triangular() = (this * (this + 1) / 2).toShort()
fun Int.triangular() = this * (this + 1) / 2
fun Long.triangular() = this * (this + 1) / 2
fun Float.triangular() = this * (this + 1) / 2
fun Double.triangular() = this * (this + 1) / 2