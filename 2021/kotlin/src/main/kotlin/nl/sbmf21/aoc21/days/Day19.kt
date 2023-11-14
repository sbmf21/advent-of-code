package nl.sbmf21.aoc21.days

import nl.sbmf21.aoc.common.ADay
import nl.sbmf21.aoc.common.iterated
import nl.sbmf21.math.Vector3i
import kotlin.math.abs

class Day19 : ADay() {

    private val scannerPattern = Regex("--- scanner (?<id>\\d+) ---")
    private val beaconPattern = Regex("(?<x>-?\\d+),(?<y>-?\\d+),(?<z>-?\\d+)")
    private val scanners: List<Scanner>
    private val rotations = listOf(
        Vector3i(0, 0, 0),
        Vector3i(0, 1, 0),
        Vector3i(0, 2, 0),
        Vector3i(0, 3, 0),
        Vector3i(0, 0, 1),
        Vector3i(0, 0, 3),
    ).map { (0..3).map { x -> Vector3i(x, it.y, it.z) } }.flatten()

    init {
        val scanners = input
            .flatMapIndexed { index, x ->
                when {
                    index == 0 || index == input.lastIndex -> listOf(index)
                    x.isEmpty() -> listOf(index - 1, index + 1)
                    else -> emptyList()
                }
            }
            .windowed(2, 2) { (from, to) -> input.slice(from..to) }
            .map {
                Scanner(scannerPattern.matchEntire(it[0])!!.toInt("id"), beacons = it.subList(1, it.size).map { b ->
                    beaconPattern.matchEntire(b)!!.run { Vector3i(toInt("x"), toInt("y"), toInt("z")) }
                })
            }
            .toMutableList()
        val goodScanners = mutableListOf(scanners.removeFirst())

        while (scanners.isNotEmpty()) (goodScanners.indices).forEach { g ->
            scanners.iterated { mutableIterator, bad ->
                findOffset(goodScanners[g], bad)?.also {
                    mutableIterator.remove()
                    goodScanners.add(
                        Scanner(
                            bad.id,
                            it.second,
                            it.first,
                            bad.beacons.map { b -> rotate(b, it.first) + it.second }
                        )
                    )
                }
            }
        }

        this.scanners = goodScanners
    }

    override fun part1() = scanners.map { it.beacons }.flatten().toSet().count()

    override fun part2() = scanners.map { left -> scanners.map { right -> left.position - right.position } }.flatten()
        .maxOf { abs(it.x) + abs(it.y) + abs(it.z) }

    private fun findOffset(good: Scanner, bad: Scanner): Pair<Vector3i, Vector3i>? {
        rotations.forEach { rotation ->
            val offsets = mutableMapOf<Vector3i, Int>()
            good.beacons.forEach { goodBeacon ->
                bad.beacons.forEach { badBeacon ->
                    val offset = goodBeacon - rotate(badBeacon, rotation)
                    offsets[offset] = (offsets[offset] ?: 0) + 1
                    if (offsets[offset]!! >= 12) return Pair(rotation, offset)
                }
            }
        }; return null
    }

    private fun rotate(beacon: Vector3i, rotation: Vector3i) = beacon
        .run { (0 until rotation.z).fold(this) { beacon, _ -> Vector3i(-beacon.y, beacon.x, beacon.z) } }
        .run { (0 until rotation.y).fold(this) { beacon, _ -> Vector3i(beacon.z, beacon.y, -beacon.x) } }
        .run { (0 until rotation.x).fold(this) { beacon, _ -> Vector3i(beacon.x, -beacon.z, beacon.y) } }

    private data class Scanner(
        val id: Int,
        var position: Vector3i = Vector3i(0, 0, 0),
        var rotation: Vector3i = Vector3i(0, 0, 0),
        val beacons: List<Vector3i>,
    )
}