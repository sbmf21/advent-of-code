package io.frutsel_.aoc.common

fun chineseRemainder(n: List<Long>, a: List<Long>): Long {
    val prod = n.fold(1L) { acc, i -> acc * i }
    var sum = 0L

    for (i in n.indices) {
        val p = prod / n[i]
        sum += a[i] * modInv(p, n[i]) * p
    }

    return mod(sum, prod)
}

fun mod(a: Long, b: Long) = a.toBigInteger()
    .mod(b.toBigInteger())
    .toLong()

fun modInv(a: Long, b: Long) = a.toBigInteger()
    .modInverse(b.toBigInteger())
    .toLong()
