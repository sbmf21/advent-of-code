package io.frutsel_.aoc;

import io.frutsel_.aoc.day1.Day1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class AdventOfCode {

    public static void main(String[] args) {

        var days = new IDay[]{new Day1()};

        Arrays.asList(days).forEach(AdventOfCode::runDay);
    }

    public static BufferedReader getResource(String fileName) {
        fileName = String.format("/%s.txt", fileName);
        var inputStream = AdventOfCode.class.getResourceAsStream(fileName);

        return new BufferedReader(new InputStreamReader(inputStream));
    }

    private static void runDay(IDay day) {
        System.out.printf("Day %d%n", day.dayNumber());

        for (var part: day.parts()) {
            System.out.printf("- Part %d%n", part.partNumber());
            part.solve();

            System.out.println();
        }
    }
}
