package io.frutsel_.aoc;

import io.frutsel_.aoc.common.ADay;
import io.frutsel_.aoc.common.AocBase;

public class Aoc extends AocBase implements Runnable {

    public static void main(String[] args) {
        var aoc = new Aoc();

        aoc.init(args);
        aoc.run();
    }

    public Aoc() {
        super("2015");
    }

    @Override
    public void run() {
        this.findDays().forEach(this::runDay);

        ReportGenerator.print();
    }

    private void runDay(ADay day) {
        var start = System.currentTimeMillis();
        var part1 = day.part1();
        var m = System.currentTimeMillis();
        var part2 = day.part2();
        var end = System.currentTimeMillis();

        ReportGenerator.add(day, start, m, end, part1, part2);
    }
}
