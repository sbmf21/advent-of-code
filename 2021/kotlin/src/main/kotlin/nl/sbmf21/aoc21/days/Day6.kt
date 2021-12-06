package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay

class Day6(input: List<String>) : ADay(input) {

    private val fish = input[0].split(",").map { it.toInt() }

    override fun part1() = shuffleFish(80)

    override fun part2() = shuffleFish(256)

    private fun shuffleFish(days: Int): Double {
        val fish = doubleArrayOf(0.0, catch(1), catch(2), catch(3), catch(4), catch(5), 0.0, 0.0, 0.0).toMutableList()

        for (d in 0 until days) {
            val newFish = fish[0]
            for (i in 0..7) fish[i] = fish[i + 1]
            fish[6] += newFish
            fish[8] = newFish
        }

        return fish.sum()
    }

    private fun catch(age: Int) = fish.count { it == age }.toDouble()
}
