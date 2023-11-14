package nl.sbmf21.aoc22.days

import nl.sbmf21.aoc.common.Day

class Day7 : Day() {

    private val root: Dir = mapFileStructure()
    private val directories: List<Dir> = getDirectories(root).sortedBy { it.size }
    private val freeSpace: Int = 70_000_000 - root.size

    override fun part1() = getSizesBelow100k(root)

    override fun part2() = directories.first { freeSpace + it.size > 30_000_000 }.size

    private fun mapFileStructure(): Dir {
        lateinit var root: Dir
        var current: Dir? = null

        input.fold(mutableListOf<Command>()) { commands, it ->
            if (it[0] == '$') commands.add(Command(it.substring(2)))
            else commands.last().output += it
            commands
        }.forEach { cmd ->
            when (cmd.command) {
                "cd" -> current =
                    if (current == null) Dir(cmd.arg!!).also { root = it }
                    else if (cmd.arg == "..") current!!.parent
                    else current!!.files.first { it is Dir && it.name == cmd.arg } as Dir

                "ls" -> cmd.output.forEach {
                    val parts = it.split(" ", limit = 2)
                    current!!.files +=
                        if (parts[0] == "dir") Dir(parts[1], current)
                        else File(parts[1], parts[0].toInt(), current!!)
                }
            }
        }

        return root
    }

    private fun getDirectories(dir: Dir): List<Dir> {
        val list = mutableListOf(dir)
        for (f in dir.files.filterIsInstance<Dir>()) list += getDirectories(f)
        return list
    }

    private fun getSizesBelow100k(dir: Dir): Int {
        var size = if (dir.size <= 100_000) dir.size else 0
        for (file in dir.files.filterIsInstance<Dir>()) size += getSizesBelow100k(file)
        return size
    }

    private class Command(input: String) {
        val command: String
        val arg: String?
        val output = mutableListOf<String>()

        init {
            val data = input.split(" ", limit = 2)
            command = data[0]
            arg = if (data.size > 1) data[1] else null
        }
    }

    private interface FileData {
        val size: Int
    }

    @Suppress("unused") // name and parent are unused, but I like 'em
    private class File(val name: String, override val size: Int, val parent: Dir) : FileData

    private class Dir(val name: String, val parent: Dir? = null) : FileData {
        val files = mutableListOf<FileData>()

        override val size: Int get() = files.sumOf { it.size }
    }
}