package io.frutsel_.aoc.day1;

import io.frutsel_.aoc.ADay;
import io.frutsel_.aoc.APart;

import java.io.IOException;

public class Day1 extends ADay {

    public char[] loadInstructions() throws IOException {
        try (var reader = getResource()) {
            StringBuilder input = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                input.append(line);
            }

            return input.toString().toCharArray();
        }
    }

    @Override
    public APart<?>[] parts() {
        return new APart[]{
                new Part1(this), //
                new Part2(this)
        };
    }

    @Override
    public int dayNumber() {
        return 1;
    }
}
