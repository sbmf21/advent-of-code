package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay

class Day1 : ADay() {

    private val elves = getElves(input)

    override fun part1() = elves.maxOf { it.sum() }

    override fun part2() = elves.map { it.sum() }
        .sortedDescending()
        .subList(0, 3)
        .sum()

    private fun getElves(input: List<String>): List<List<Int>> {
        val elves = mutableListOf<List<Int>>()
        var elf = mutableListOf<Int>()

        for (item in input) {
            if (item.isEmpty()) {
                addElf(elves, elf)
                elf = mutableListOf()
            } else {
                elf.add(item.toInt())
            }
        }

        addElf(elves, elf)

        return elves
    }

    private fun addElf(elves: MutableList<List<Int>>, elf: List<Int>) {
        if (elf.isEmpty()) return
        if (elves.contains(elf)) return
        elves.add(elf)
    }
}