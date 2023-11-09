package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.ADay

class Day11(input: List<String>) : ADay(input) {

    private val monkeys: List<Monkey>
    private val mod: ULong

    init {
        var mod = 1UL

        monkeys = input.chunked(7).map {
            val opParts = it[2].split(": ")[1].split(" ")
            val testBy = it[3].split(" ").last().toULong()
            mod *= testBy

            Monkey(
                it[1].split(": ")[1].split(", ").map { i -> MonkeyItem(i.toULong()) }.toMutableList(),
                when (opParts[3]) {
                    "+" -> MonkeyOperation(opParts[4], ULong::plus)
                    "*" -> MonkeyOperation(opParts[4], ULong::times)
                    else -> throw Error(opParts[3])
                },
                { i -> i.worryLevel % testBy == 0UL },
                it[4].split(" ").last().toInt(),
                it[5].split(" ").last().toInt(),
            )
        }

        this.mod = mod
    }

    override fun part1() = run(20, true)

    override fun part2() = run(10_000, false)

    private fun run(rounds: Int, reduce: Boolean): Long {
        var monkeys = monkeys.map { it.copy() }

        for (i in 1..rounds) {
            monkeys.forEach { monkey ->
                monkey.items.onEach {
                    monkey.inspect(it)
                    if (reduce) it.worryLevel /= 3u else it.worryLevel %= mod
                    monkeys[monkey.to(it)].items.add(it)
                }.clear()
            }
        }

        monkeys = monkeys.sortedByDescending { it.inspections }
        return monkeys[0].inspections * monkeys[1].inspections
    }
}

private class Monkey(
    val items: MutableList<MonkeyItem>,
    private val operation: MonkeyOperation,
    private val test: (MonkeyItem) -> Boolean,
    private val trueTo: Int,
    private val falseTo: Int,
) {
    var inspections: Long = 0
        private set

    fun inspect(item: MonkeyItem) {
        inspections++
        operation(item)
    }

    fun to(item: MonkeyItem) = if (test(item)) trueTo else falseTo

    fun copy() = Monkey(items.map { it.copy() }.toMutableList(), operation, test, trueTo, falseTo)
}

private class MonkeyItem(var worryLevel: ULong) {
    fun copy(): MonkeyItem = MonkeyItem(worryLevel)
}

private class MonkeyOperation(by: String, private val run: (ULong, ULong) -> ULong) {

    val old = by == "old"
    val by = if (old) 0UL else by.toULong()

    operator fun invoke(item: MonkeyItem) {
        item.worryLevel = run(item.worryLevel, if (old) item.worryLevel else by)
    }
}