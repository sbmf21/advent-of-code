package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay

class Day24(input: List<String>) : ADay(input) {

    private val instructions = input.map { it.split(" ") }

    @Volatile
    private var current = 99999999999999

    @Volatile
    private var found: Long? = null

    override fun part1(): Any {
        val threads = mutableListOf<Thread>()

//        for (t in 1..Runtime.getRuntime().availableProcessors()) {
//            threads.add(thread {
//                val localInstructs = instructions.map { it.map { it } }
//                var count = 0
//
//                while (found == null) {
//
//                    var i = next()
//                    count++
//                    val nrs = "$i".toCharArray().map { "$it".toInt() }.toMutableList()
//                    val alu = Alu()
//
//                    if (nrs.contains(0)) continue
//
//                    localInstructs.forEach {
//                        when (it[0]) {
//                            "inp" -> alu.write(it[1], nrs.removeFirst())
//                            "add" -> alu.write(it[1], alu.read(it[2]) + alu.read(it[1]))
//                            "mul" -> alu.write(it[1], alu.read(it[1]) * alu.read(it[2]))
//                            "div" -> alu.write(
//                                it[1],
//                                floor(alu.read(it[1]).toDouble() / alu.read(it[2]).toDouble()).toInt()
//                            )
//                            "mod" -> alu.write(it[1], alu.read(it[1]) % alu.read(it[2]))
//                            "eql" -> alu.write(it[1], if (alu.read(it[1]) == alu.read(it[2])) 1 else 0)
//                            else -> throw IllegalStateException("encountered invalid operation: ${it[0]}")
//                        }
//                    }
//                    i--
//
////                    if (nrs.isNotEmpty()) throw IllegalStateException("Not all numbers were used! " + i)
//                    if (alu.z == 0) {
//                        println(i)
//                        synchronized(instructions) { found = i }
//                    }
//                }
//
//                println(Thread.currentThread().name + " check " + count + "numbers")
//                threads.remove(Thread.currentThread())
//            })
//        }
//
//        while (threads.any { it.isAlive }) {
//            Thread.sleep(1000)
//        }

        return found ?: -1
    }

    fun next() = synchronized(current) { if (current == 11111111111111) -1 else --current }

    override fun part2(): Any {

        return -1
    }
}

internal data class Alu(var x: Int = 0, var y: Int = 0, var z: Int = 0, var w: Int = 0) {

    fun write(address: String, value: Int) = when (address) {
        "x" -> x = value
        "y" -> y = value
        "z" -> z = value
        "w" -> w = value
        else -> throw IllegalStateException("encountered invalid address: $address")
    }

    fun read(address: String) = if (address in listOf("x", "y", "z", "w")) when (address) {
        "x" -> x
        "y" -> y
        "z" -> z
        "w" -> w
        else -> throw IllegalStateException("encountered invalid address: $address")
    } else address.toInt()
}