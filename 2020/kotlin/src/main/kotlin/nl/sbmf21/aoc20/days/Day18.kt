package nl.sbmf21.aoc20.days

import nl.sbmf21.aoc.common.Day
import nl.sbmf21.aoc.common.iterated

class Day18 : Day() {

    private val expressions = input.map { line ->
        line.toCharArray().filter { it != ' ' }.fold(Block()) { acc, xpr ->
            when (xpr) {
                '(' -> Block(acc).also { acc.addXpr(it) }
                ')' -> acc.parent!!
                else -> acc.also { acc.addXpr(xpr) }
            }
        }
    }

    override fun part1() = run()

    override fun part2() = run(true)

    private fun run(order: Boolean = false) = expressions.sumOf { it.exec(order).value }

    private class Block(val parent: Block? = null) : Expr {

        private val parts = mutableListOf<Expr>()

        fun addXpr(xpr: Any) = parts.add(
            when (xpr) {
                '+' -> Plus()
                '*' -> Times()
                is Block -> xpr
                else -> ValExpr("$xpr".toLong())
            }
        )

        fun exec(order: Boolean): ValExpr {
            val parts = parts.map { e -> if (e is Block) e.exec(order) else e }.toMutableList()

            if (order) parts.filterIsInstance<ExecExpr>().filter { it.first() }.forEach { e ->
                val i = parts.indexOf(e) - 1
                val pref = parts[i] as ValExpr
                val next = parts[i + 2] as ValExpr

                parts.removeAll(setOf(pref, e, next))
                parts.add(i, e.exec(pref, next))
            }

            var value: ValExpr = parts.first() as ValExpr

            parts.filterIndexed { i, _ -> i > 0 }
                .toMutableList()
                .iterated { i, e -> if (e is ExecExpr) value = e.exec(value, i.next() as ValExpr) }

            return value
        }
    }

    private interface Expr

    private class ValExpr(val value: Long) : Expr {
        operator fun plus(v: ValExpr) = ValExpr(value + v.value)
        operator fun times(v: ValExpr) = ValExpr(value * v.value)
    }

    private interface ExecExpr : Expr {
        fun exec(prev: ValExpr, next: ValExpr): ValExpr
        fun first(): Boolean
    }

    private class Plus : ExecExpr {
        override fun exec(prev: ValExpr, next: ValExpr) = prev + next
        override fun first() = true
    }

    private class Times : ExecExpr {
        override fun exec(prev: ValExpr, next: ValExpr) = prev * next
        override fun first() = false
    }
}