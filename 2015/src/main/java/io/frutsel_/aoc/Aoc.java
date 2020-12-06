package io.frutsel_.aoc;

import org.reflections.Reflections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;

public class Aoc implements Runnable {

    public static void main(String[] args) {
        new Aoc().run();
    }

    private final StringBuilder rapport = new StringBuilder();

    public Aoc() {
        addLine("Advent of Code 2015");
    }

    @Override
    public void run() {
        this.findDays().forEach(this::runDay);

        System.out.print(rapport);
    }

    private void runDay(ADay day) {
        addLine("%nDay %d", day.number());

        addLine("- Part 1");
        addLine("  Answer: %s", day.part1());
        addLine("- Part 2");
        addLine("  Answer: %s", day.part2());
    }

    public ArrayList<ADay> findDays() {
        var days = new ArrayList<ADay>();

        new Reflections(String.format("%s.days", ADay.class.getPackageName()))
                .getSubTypesOf(ADay.class)
                .forEach(dayClass -> addDay(days, dayClass));

        days.sort(Comparator.comparingInt(ADay::number));

        return days;
    }

    private void addDay(ArrayList<ADay> days, Class<? extends ADay> dayClass) {
        try {
            var day = dayClass.getDeclaredConstructor(Aoc.class).newInstance(this);

            days.add(day);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();

            System.exit(-1);
        }
    }

    public ArrayList<String> file(ADay day) {
        var lines = new ArrayList<String>();

        try (var reader = new BufferedReader(getInputStream(day))) {
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        return lines;
    }

    private InputStreamReader getInputStream(ADay day) {
        return new InputStreamReader(Aoc.class.getResourceAsStream(getFileName(day)));
    }

    private String getFileName(ADay day) {
        return String.format("/input/day%d.txt", day.number());
    }

    private void addLine(String format, Object... args) {
        rapport.append(String.format(format, args)).append('\n');
    }
}
