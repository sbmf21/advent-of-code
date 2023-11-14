package nl.sbmf21.aoc20.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.math.Vector3i
import nl.sbmf21.math.Vector4i

class Day17 : ADay() {

    private val bootRange = -12..12
    private val neighbors = -1..1

    override fun part1(): Int {

        var actives = mutableSetOf<Vector3i>()
        for (r in input.indices) for (c in input[r].indices) if (input[r][c] == '#') actives.add(Vector3i(r, c, 0))

        for (cycle in 1..6) {
            val newActives = mutableSetOf<Vector3i>()

            for (x in bootRange) for (y in bootRange) for (z in bootRange) {
                var nbr = 0

                for (dx in neighbors) for (dy in neighbors) for (dz in neighbors) {
                    if (dx == 0 && dy == 0 && dz == 0) continue
                    if (actives.contains(Vector3i(x + dx, y + dy, z + dz))) nbr++
                }

                val current = Vector3i(x, y, z)
                if (!actives.contains(current) && nbr == 3) newActives.add(current)
                if (actives.contains(current) && nbr in 2..3) newActives.add(current)
            }

            actives = newActives
        }

        return actives.size
    }

    override fun part2(): Any {
        var actives = mutableSetOf<Vector4i>()
        for (r in input.indices) for (c in input[r].indices) if (input[r][c] == '#') actives.add(Vector4i(r, c, 0, 0))

        for (cycle in 1..6) {
            val newActives = mutableSetOf<Vector4i>()

            for (x in bootRange) for (y in bootRange) for (z in bootRange) for (w in bootRange) {
                var count = 0

                for (dx in neighbors) for (dy in neighbors) for (dz in neighbors) for (dw in neighbors) {
                    if (dx == 0 && dy == 0 && dz == 0 && dw == 0) continue
                    if (actives.contains(Vector4i(x + dx, y + dy, z + dz, w + dw))) count++
                }

                val current = Vector4i(x, y, z, w)
                if (actives.contains(current) && count in 2..3) newActives.add(current)
                if (!actives.contains(current) && count == 3) newActives.add(current)
            }

            actives = newActives
        }

        return actives.size
    }
}