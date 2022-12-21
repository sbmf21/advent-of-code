package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc22.days.Day21.Operator.*

class Day21(input: List<String>) : ADay(input) {

    private val numberRegex = Regex("\\d+")
    private val monkeys = input.fold(mutableMapOf<String, Node>()) { map, line ->
        val (name, value) = line.split(": ", limit = 2)
        map[name] = if (value.matches(numberRegex)) NumberNode(name, value.toLong())
        else value.split(" ", limit = 3).let { (left, op, right) -> OperationNode(name, op, left, right) }
        map
    }

    override fun part1() = monkeys["root"]!!.value

    override fun part2(): Long {
        val (rLeft, rRight) = (monkeys["root"] as OperationNode).let { it.left to it.right }
        val operations = mutableListOf<OperationNode>()
        val values = mutableMapOf<Node, Long>()

        if (hasHuman(rLeft)) (rLeft as OperationNode).let {
            operations += it
            values[it] = rRight.value
        } else if (hasHuman(rRight)) (rRight as OperationNode).let {
            operations += it
            values[it] = rLeft.value
        }

        while (true) {
            val operation = operations.removeFirst()
            val (left, right) = operation.left to operation.right

            if (isHuman(left)) return reverseLeft(operation, values[operation]!!, right.value)
            else if (hasHuman(left)) operations.add(left as OperationNode)
            else left.value.let {
                values[left] = it
                values[right] = reverseRight(operation, values[operation]!!, it)
            }

            if (isHuman(right)) return reverseRight(operation, values[operation]!!, left.value)
            else if (hasHuman(right)) operations.add(right as OperationNode)
            else right.value.let {
                values[left] = reverseLeft(operation, values[operation]!!, it)
                values[right] = it
            }
        }
    }

    private fun reverseLeft(operation: OperationNode, expected: Long, other: Long) = when (operation.operator) {
        PLUS -> expected - other
        MINUS -> expected + other
        TIMES -> expected / other
        DIV -> expected * other
    }

    private fun reverseRight(operation: OperationNode, expected: Long, other: Long) = when (operation.operator) {
        PLUS -> expected - other
        MINUS -> other - expected
        TIMES -> expected / other
        DIV -> other / expected
    }

    private fun hasHuman(node: Node): Boolean = when {
        isHuman(node) -> true
        node is OperationNode -> hasHuman(node.left) || hasHuman(node.right)
        else -> false
    }

    private fun isHuman(node: Node) = node is NumberNode && node.name == "humn"

    private enum class Operator(val key: String, private val operator: (Long, Long) -> Long) {
        PLUS("+", Long::plus),
        MINUS("-", Long::minus),
        TIMES("*", Long::times),
        DIV("/", Long::div);

        operator fun invoke(left: Long, right: Long) = operator(left, right)
    }

    private interface Node {
        val name: String
        val value: Long
    }

    private class NumberNode(override val name: String, override val value: Long) : Node

    private inner class OperationNode(override val name: String, operator: String, left: String, right: String) : Node {
        val operator = Operator.values().first { it.key == operator }
        val left by lazy { monkeys[left]!! }
        val right by lazy { monkeys[right]!! }
        override val value by lazy { operator(this.left.value, this.right.value) }
    }
}
