package nl.sbmf21.aoc.common

import org.reflections.Reflections
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*
import java.util.regex.Pattern
import kotlin.concurrent.thread
import kotlin.math.min
import kotlin.math.round
import kotlin.system.measureNanoTime

abstract class AocBase(val name: String, val simulations: Map<String, (AocBase) -> Simulation<*>> = mapOf()) {

    private var report: Report = makeReport()
    private var pattern = Pattern.compile("--day(?:=(?<day>\\d+))?")
    internal var runDay: Int? = null
        private set
    internal var runSim: String? = null
        private set
    internal var hideAnswers = false
        private set

    val days = Reflections("${this::class.java.packageName}.days")
        .getSubTypesOf(ADay::class.java)
        .filter { it.simpleName.matches(Regex("Day\\d+")) }
        .map { DayMeta(it) }
        .sortedBy { it.number }

    fun exec(args: Array<String>) {
        init(args)
        runDays()
        report()
    }

    internal fun init(args: Array<String>, inputStream: InputStream = System.`in`) = args.forEach { arg ->
        val matcher = pattern.matcher(arg)

        simulations.keys.forEach { sim ->
            if (args.contains("--$sim")) {
                setSim(sim)
            }
        }

        if (matcher.matches()) {
            val day = matcher.group("day")
            if (day == null) {
                print("Enter day to run: ")
                setDay(BufferedReader(InputStreamReader(inputStream)).readLine()?.toInt())
            } else {
                setDay(day.toInt())
            }
        }

        if (arg.matches(Regex("--latest"))) {
            setDay(days.last().number)
        }

        if (arg.matches(Regex("--hide"))) {
            hideAnswers = true
        }
    }

    private fun setDay(day: Int?) {
        checkRunning()
        runDay = day
    }

    private fun setSim(sim: String?) {
        checkRunning()
        runSim = sim
    }

    private fun checkRunning() {
        if (runSim != null) throw Error("Already running a simulation")
        if (runDay != null) throw Error("Already running a specific day")
    }

    private fun runDays() {
        if (runSim != null) {
            simulations.firstNotNullOf { if (it.key == runSim) it.value else null }
                .invoke(this)
                .simulate()
        } else if (runDay != null) {
            days.filter { it.number == runDay }.forEach { report.run(it) }
        } else {
            val threads = mutableListOf<Thread>()
            val concurrentDays = Collections.synchronizedList(days.toMutableList())
            val threadCount = min(days.size, Runtime.getRuntime().availableProcessors())

            println("Running in $threadCount thread${if (threadCount == 1) "" else "s"}")
            for (t in 1..threadCount) threads.add(thread {
                while (concurrentDays.isNotEmpty()) report.run(concurrentDays.removeFirst())
            })
            for (thread in threads) thread.join()
        }
    }

    private fun report() = report.render()
    private fun makeReport() = Report(this)
}

inline fun <reified T : ADay, reified S : Simulation<T>> buildSimulation(crossinline build: (T) -> S): (AocBase) -> S {

    return {
        val day = it.days.first { it.clazz == T::class.java }.build() as T
        println("Simulating day ${day.number}")
        build.invoke(day)
    }
}

internal const val s_ = 1_000_000_000.0
internal const val ms = 1_000_000.0
internal const val us = 1_000.0

internal class TimedRunner(internal val meta: DayMeta<ADay>) {

    private lateinit var day: ADay
    private var totalTime: Long? = null
    private var setupTime: Long? = null
    private var part1Time: Long? = null
    private var part2Time: Long? = null
    private lateinit var part1Value: Any
    private lateinit var part2Value: Any

    fun day() = day
    fun totalTime() = timeString(totalTime)
    fun setupTime() = timeString(setupTime)
    fun part1Time() = timeString(part1Time)
    fun part2Time() = timeString(part2Time)
    fun part1Value() = stringifyNumber(part1Value)
    fun part2Value() = stringifyNumber(part2Value)

    internal fun run() {
        totalTime = measureNanoTime {
            setupTime = measureNanoTime { day = meta.build() }
            part1Time = measureNanoTime { part1Value = day.part1() }
            part2Time = measureNanoTime { part2Value = day.part2() }
        }
    }

    private fun timeString(time: Long?) = when {
        time == null -> "NaN   "
        time > s_ -> "${format(time, s_)}  s"
        time > ms -> "${format(time, ms)} ms"
        time > us -> "${format(time, us)} μs"
        else -> "$time ns"
    }

    private fun format(time: Long, value: Double): String = (round((time / value) * 100) / 100).toString()

    private fun stringifyNumber(n: Any?) = when (n) {
        null -> "none"
        is Long -> n.toBigInteger().toString()
        is Double -> n.toBigDecimal().toString()
        else -> n.toString()
    }
}
