package nl.sbmf21.aoc.common

import org.reflections.Reflections
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*
import kotlin.concurrent.thread
import kotlin.math.min

abstract class AocBase(val name: String, val simulations: Map<String, (AocBase) -> Simulation<*>> = mapOf()) {

    private var report: Report = makeReport()
    internal var runDay: Int? = null
        private set
    internal var runSim: String? = null
        private set

    val days = Reflections("${this::class.java.packageName}.days")
        .getSubTypesOf(Day::class.java)
        .filter { it.simpleName.matches(Regex("Day\\d+")) }
        .map { DayMeta(it) }
        .sortedBy { it.number }

    fun exec(args: Array<String>) {
        init(args)
        runDays()
    }

    internal fun init(args: Array<String>, inputStream: InputStream = System.`in`) = args.forEach { arg ->
        simulations.keys.forEach { sim ->
            if (args.contains("--$sim")) {
                setSim(sim)
            }
        }

        Regex("--day(?:=(?<day>\\d+))?").find(arg)?.let { match ->
            val day = match.groups["day"]
            if (day == null) {
                print("Enter day to run: ")
                setDay(BufferedReader(InputStreamReader(inputStream)).readLine()?.toInt())
            } else {
                setDay(day.value.toInt())
            }
        }

        if (arg.matches(Regex("--latest"))) {
            setDay(days.last().number)
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
            report()
        } else {
            val threads = mutableListOf<Thread>()
            val concurrentDays = Collections.synchronizedList(days.toMutableList())
            val threadCount = min(days.size, Runtime.getRuntime().availableProcessors())

            println("Running in $threadCount thread${if (threadCount == 1) "" else "s"}")
            for (t in 1..threadCount) threads.add(thread {
                while (concurrentDays.isNotEmpty()) report.run(concurrentDays.removeFirst())
            })
            for (thread in threads) thread.join()
            report()
        }
    }

    private fun report() = report.render()
    private fun makeReport() = Report(this)
}