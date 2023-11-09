package nl.sbmf21.aoc22.days.simulations

import nl.sbmf21.aoc.common.*
import nl.sbmf21.aoc22.days.Day10

class CRT(day: Day10) : Simulation<Day10>() {

    override val frames: List<Frame>

    init {
        framesCount = 15
        val frameData = mutableListOf<CRTFrameData>()

        day.store = { cycle, operation, operationPart, x, strength, crt, row, i ->
            frameData.add(CRTFrameData(cycle, operation, operationPart, x, strength, crt, row, i))
        }
        day.run()

        val maxCycle = frameData.maxOf { it.cycle.toString().length }
        val maxX = frameData.maxOf { it.x.toString().length }
        val maxStrength = frameData.maxOf { it.strength.toString().length }
        val maxI = frameData.maxOf { it.i.toString().length }

        this.frames = frameData.map { it.toFrame(maxCycle, maxX, maxStrength, maxI) }
    }
}

private const val CRT_BLOCK = "█"

private class CRTFrameData(
    val cycle: Int,
    val operation: String,
    val operationPart: Int,
    val x: Int,
    val strength: Int,
    crt: List<List<Int>>,
    val row: Int,
    val i: Int,
) {
    val crt = crt.map { l -> l.map { it } }

    fun toFrame(maxCycle: Int, maxX: Int, maxStrength: Int, maxI: Int): CRTFrame {
        val lines = mutableListOf<String>()
        lines += headers(10, 11)
        lines += line()

        lines += crt
            .map { it.joinToString("") { digit -> if (digit == 1) CRT_BLOCK else " " } }
            .map { line(CRT_BLOCK + BACK_DARK_GREEN + LIGHT_GREEN + it + RESET + GRAY + CRT_BLOCK) }
        lines += line()
        lines += line()

        lines += line(
            "cycle: " + info(cycle, maxCycle),
            "x: " + info(x, maxX),
            "strength: " + info(strength, maxStrength),
        )

        lines += line(
            "writing to: " + row + ":" + info(i, maxI, '0'),
            "operation: " + operation.run {
                val parts = split(" ", limit = 2)
                if (parts.size == 1) this
                else parts.mapIndexed { i, it -> color(operationPart, i) + it }.joinToString(" ")
            },
        )

        return CRTFrame(lines.joinToString("\n"))
    }

    private fun line(string: String = CRT_BLOCK.repeat(42)) = GRAY + string + RESET

    private fun line(vararg list: String) = line(list.joinToString(", "))

    private fun color(expected: Int, actual: Int): String {
        return if (expected == actual) WHITE else GRAY
    }

    private fun info(value: Int, length: Int, char: Char = ' '): String {
        return char.toString().repeat(length - value.toString().length) + value
    }
}

private class CRTFrame(override val content: String) : Frame()