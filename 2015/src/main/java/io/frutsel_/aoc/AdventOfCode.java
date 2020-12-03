package io.frutsel_.aoc;

import io.frutsel_.aoc.day1.Day1;
import io.frutsel_.aoc.day2.Day2;
import io.frutsel_.aoc.day3.Day3;

import java.util.Arrays;
import java.util.List;

public class AdventOfCode implements Runnable {

    private static final char NEW_LINE = '\n';

    public static void main(String[] args) {

        var days = new ADay[]{
                new Day1(), //
                new Day2(), //
                new Day3()
        };

        new AdventOfCode(days).run();
    }

    private final StringBuilder rapport = new StringBuilder();
    private final List<ADay> days;

    private AdventOfCode(ADay[] days) {
        addLine("Advent of Code 2015");
        this.days = Arrays.asList(days);
    }

    @Override
    public void run() {
        this.days.forEach(this::runDay);

        System.out.println(rapport.toString());
    }

    private void runDay(ADay day) {
        addLine("%nDay %d", day.dayNumber());

        for (var part : day.parts()) {
            addLine("- Part %d", part.partNumber());

            try {
                addLine("  Answer: %s", part.solve());
            } catch (Exception e) {
                addLine("  Error: %s", e.getMessage());
            }
        }
    }

    private void addLine(String format, Object... args) {
        rapport.append(String.format(format, args)).append(NEW_LINE);
    }
}
