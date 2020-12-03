package io.frutsel_.aoc;

import io.frutsel_.aoc.day1.Day1;
import io.frutsel_.aoc.day2.Day2;
import io.frutsel_.aoc.day3.Day3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class AdventOfCode {

    public static void main(String[] args) {

        var days = new IDay[]{
                new Day1(), //
                new Day2(), //
                new Day3()
        };

        System.out.println("Advent of Code 2015");
        Arrays.asList(days).forEach(AdventOfCode::runDay);
    }

    public static BufferedReader getResource(String fileName) {
        fileName = String.format("/%s.txt", fileName);
        var inputStream = AdventOfCode.class.getResourceAsStream(fileName);

        return new BufferedReader(new InputStreamReader(inputStream));
    }

    private static void runDay(IDay day) {
        System.out.printf("%nDay %d%n", day.dayNumber());

        for (var part: day.parts()) {
            System.out.printf("- Part %d%n", part.partNumber());

            try {
                System.out.printf("  Answer: %s%n", part.solve());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
