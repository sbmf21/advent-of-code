package io.frutsel_.aoc.day1;

import io.frutsel_.aoc.APart;
import io.frutsel_.aoc.AdventOfCode;
import io.frutsel_.aoc.IDay;

import java.io.IOException;

public class Day1 implements IDay {

    public char[] loadInstructions() {
        try (var reader = AdventOfCode.getResource("directions")) {
            StringBuilder input = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                input.append(line);
            }

            return input.toString().toCharArray();
        } catch (IOException e) {
            e.printStackTrace();
            return new char[0];
        }
    }

    @Override public APart<?>[] parts() {
        return new APart[]{
                new Part1(this), //
                new Part2(this)
        };
    }

    @Override public int dayNumber() {
        return 1;
    }
}
