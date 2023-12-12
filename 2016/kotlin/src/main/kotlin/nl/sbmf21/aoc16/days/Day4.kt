package nl.sbmf21.aoc16.days

import nl.sbmf21.aoc.common.Day

class Day4 : Day() {

    private val validRooms = input
        .map { room ->
            val match = REGEX.matchEntire(room)!!
            Room(
                match.groupValues[1],
                match.groupValues[2].toInt(),
                match.groupValues[3],
            )
        }
        .filter { (encrypted, _, checksum) ->
            val check = encrypted.replace("-", "")
            val counts = check.map { c -> c to check.count { it == c } }

            checksum == counts
                .sortedByDescending { it.second }
                .map { current ->
                    counts
                        .filter { current.second == it.second }
                        .map { it.first }
                        .distinct()
                        .sorted()
                }
                .distinct()
                .joinToString("") { it.joinToString("") }
                .substring(0 until 5)
        }

    override fun part1() = validRooms.sumOf(Room::sectorId)

    override fun part2(): Any {
        val names = validRooms.associate { room ->
            val name = room.encrypted.split("-").joinToString(" ") { name ->
                name.toCharArray().joinToString("") { c ->
                    ('a' + ((c - 'a' + room.sectorId) % ('z' - 'a' + 1))).toString()
                }
            }

            name to room.sectorId
        }

        return names["northpole object storage"] ?: -1
    }

    private companion object {
        val REGEX = Regex("""((?:-?[a-z]+)+)-([0-9]{3})\[([a-z]{5})]""")
    }

    private data class Room(val encrypted: String, val sectorId: Int, val checksum: String)
}