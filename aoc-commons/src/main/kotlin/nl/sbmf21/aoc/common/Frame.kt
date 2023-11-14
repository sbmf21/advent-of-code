package nl.sbmf21.aoc.common

abstract class Frame {

    abstract val content: String

    fun print() = println(content)
}