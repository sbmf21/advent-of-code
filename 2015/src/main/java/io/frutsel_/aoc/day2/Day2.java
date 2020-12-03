package io.frutsel_.aoc.day2;

import io.frutsel_.aoc.APart;
import io.frutsel_.aoc.AdventOfCode;
import io.frutsel_.aoc.IDay;

import java.io.IOException;
import java.util.ArrayList;

public class Day2 implements IDay {

    public ArrayList<Dimension> parseDimensions() {
        try (var reader = AdventOfCode.getResource("wrappingPaper")) {
            var dimensions = new ArrayList<Dimension>();

            String line;
            while ((line = reader.readLine()) != null) {
                dimensions.add(Dimension.fromLine(line));
            }

            return dimensions;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public int smallest(int[] values) {
        var smallest = values[0];

        for (int i = 1; i < values.length; i++) {
            var value = values[i];

            if (value < smallest) {
                smallest = value;
            }
        }

        return smallest;
    }

    @Override public APart<?>[] parts() {
        return new APart[]{
                new Part1(this), //
                new Part2(this)
        };
    }

    @Override public int dayNumber() {
        return 2;
    }
}
