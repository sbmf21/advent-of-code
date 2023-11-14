package nl.sbmf21.aoc22.days.simulations

import nl.sbmf21.aoc.common.Color.*
import nl.sbmf21.aoc.common.Frame
import nl.sbmf21.aoc.common.Simulation
import nl.sbmf21.aoc22.days.Day18
import nl.sbmf21.math.Vector3i
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

class LavaDroplet(day: Day18) : Simulation<Day18>() {

    private val frameData: List<DropletFrameData>
    override val frames: List<Frame>
        get() = frameData.map { it.frames[z]!! }.distinctBy { it.content }

    private val maxZ: Int
    private val minZ: Int
    private var z: Int = Int.MIN_VALUE

    init {
        var maxZ = 0
        var minZ = 0

        val frameData = mutableListOf<DropletFrameData>()

        day.store = { droplets, steam, maxX, minX, maxY, minY, frameMaxZ, frameMinZ ->
            val data = DropletFrameData(droplets, steam, maxX, minX, maxY, minY, frameMaxZ, frameMinZ)
            frameData.add(data)
            maxZ = max(maxZ, frameMaxZ)
            minZ = max(minZ, frameMinZ)
        }
        day.part2()

        this.frameData = frameData
        this.maxZ = maxZ
        this.minZ = minZ
    }

    override fun simulate() {
        while (z !in minZ..maxZ) {
            print("Enter z value to show [$minZ-$maxZ]: ")
            z = BufferedReader(InputStreamReader(System.`in`)).readLine()?.toInt()!!
        }

        super.simulate()
    }

    private class DropletFrameData(
        droplets: Set<Vector3i>,
        steam: Set<Vector3i>,
        maxX: Int,
        minX: Int,
        maxY: Int,
        minY: Int,
        maxZ: Int,
        minZ: Int,
    ) {
        val frames: Map<Int, DropletFrame>

        init {
            val frames = mutableMapOf<Int, DropletFrame>()

            for (z in minZ..maxZ) {
                val lines = mutableListOf<String>()
                for (y in maxY downTo minY) {
                    var line = ""
                    for (x in minX..maxX) {
                        val vec = Vector3i(x, y, z)
                        line += when (vec) {
                            in droplets -> "$BACK_DARK_RED  "
                            in steam -> "$BACK_WHITE  "
                            else -> "$BACK_BLACK  "
                        }
                    }
                    line += RESET
                    lines.add(line)
                }
                frames[z] = DropletFrame(lines.joinToString("\n"))
            }

            this.frames = frames
        }
    }

    private class DropletFrame(override val content: String) : Frame()
}