package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay

class Day6(input: List<String>) : ADay(input) {

    private val fish = input[0].split(",").map { it.toInt() }

    override fun part1() = shuffleFish(80)

    override fun part2() = shuffleFish(256)

    private fun shuffleFish(days: Int): Double {
        val blub = doubleArrayOf(0.0, catch(1), catch(2), catch(3), catch(4), catch(5), 0.0, 0.0, 0.0).toMutableList()

        for (d in 0 until days) {
            val newBlub = blub[0]
            for (i in 0..7) blub[i] = blub[i + 1]
            blub[6] += newBlub
            blub[8] = newBlub
        }

        return blub.sum()
    }

    private fun catch(age: Int) = fish.count { it == age }.toDouble()
}
