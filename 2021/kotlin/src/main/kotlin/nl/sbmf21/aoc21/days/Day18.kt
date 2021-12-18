package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc.common.Vector2
import kotlin.math.ceil
import kotlin.math.floor

class Day18(input: List<String>) : ADay(input) {

    private val list = buildList()
    private var indices = mutableListOf<SFRegular>()
    private var levels = mutableMapOf<SFNumber, Int>()

    override fun part1() = list.map { it.clone() }.reduce { left, right -> reduce(left, right) }.value()

    override fun part2() = list.run {
        List(size) { x -> List(size) { y -> Vector2(x, y) } }.flatten().filter { it.x != it.y }
            .maxOf { reduce(this[it.x].clone(), this[it.y].clone()).value() }
    }

    private fun buildList() = input.map { line ->
        line.substring(1).toCharArray().fold(SFPair()) { pair, it ->
            when {
                it == '[' -> SFPair(pair).also { pair.set(it) }
                it == ']' -> pair.parent ?: pair
                it.isDigit() -> SFRegular(pair, it.digitToInt()).also { pair.set(it) }.run { pair }
                else -> pair
            }
        }
    }

    private fun mapIndices(pair: SFPair, index: Int = 0): Int {
        var i = index
        pair.left.also { if (it is SFPair) i = mapIndices(it, i) else indices.add(i++, it as SFRegular) }
        pair.right.also { if (it is SFPair) i = mapIndices(it, i) else indices.add(i++, it as SFRegular) }
        return i
    }

    private fun mapLevels(pair: SFPair, level: Int = 0) {
        levels[pair] = level
        pair.left?.also { if (it is SFPair) mapLevels(it, level + 1) else levels[it] = level + 1 }
        pair.right?.also { if (it is SFPair) mapLevels(it, level + 1) else levels[it] = level + 1 }
    }

    private fun reduce(left: SFPair, right: SFPair): SFPair {
        val pair = SFPair().also { left.parent = it; right.parent = it }.also { it.left = left; it.right = right }
        while (true) {
            indices = mutableListOf(); mapIndices(pair); levels = mutableMapOf(); mapLevels(pair)
            if (!doExplode(pair) && !doSplit(pair)) return pair
        }
    }

    private fun doExplode(pair: SFPair): Boolean {
        val explode = findExplode(pair) ?: return false
        (indices.indexOf(explode.left) - 1).run { if (this >= 0) indices[this].value += explode.left!!.value() }
        (indices.indexOf(explode.right) + 1).run { if (this < indices.size) indices[this].value += explode.right!!.value() }
        SFRegular(explode.parent!!, 0).also { explode.parent?.update(explode, it); return true }
    }

    private fun findExplode(pair: SFPair): SFPair? {
        if (levels[pair] == 4) return pair
        if (pair.left is SFPair) findExplode(pair.left as SFPair)?.also { return it }
        if (pair.right is SFPair) findExplode(pair.right as SFPair)?.also { return it }
        return null
    }

    private fun doSplit(pair: SFPair): Boolean {
        val number = findSplit(pair.left) ?: findSplit(pair.right) ?: return false
        number.parent!!.also { parent ->
            parent.update(number, SFPair(parent).also {
                it.left = SFRegular(it, floor(number.value / 2.0).toInt())
                it.right = SFRegular(it, ceil(number.value / 2.0).toInt())
            })
        }; return true
    }

    private fun findSplit(number: SFNumber?): SFRegular? {
        if (number is SFRegular && number.value >= 10) return number
        if (number is SFPair) (findSplit(number.left) ?: findSplit(number.right))?.also { return it }
        return null
    }
}

internal interface SFNumber {
    fun value(): Int
    fun clone(parent: SFPair? = null): SFNumber
}

internal class SFPair(var parent: SFPair? = null) : SFNumber {
    var left: SFNumber? = null
    var right: SFNumber? = null

    fun set(number: SFNumber) = if (left == null) left = number else right = number
    fun update(old: SFNumber, new: SFNumber) = if (left == old) left = new else right = new
    override fun value() = 3 * left!!.value() + 2 * right!!.value()
    override fun clone(parent: SFPair?) = SFPair(parent)
        .also { it.left = left!!.clone(it) }
        .also { it.right = right!!.clone(it) }
}

internal class SFRegular(val parent: SFPair?, var value: Int) : SFNumber {
    override fun value() = value
    override fun clone(parent: SFPair?) = SFRegular(parent, value)
}
