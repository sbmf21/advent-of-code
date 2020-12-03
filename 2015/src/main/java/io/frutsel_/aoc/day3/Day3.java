package io.frutsel_.aoc.day3;

import io.frutsel_.aoc.APart;
import io.frutsel_.aoc.AdventOfCode;
import io.frutsel_.aoc.IDay;

import java.io.IOException;
import java.util.ArrayList;

public class Day3 implements IDay {

    public ArrayList<Direction> parseDirections() {
        try (var reader = AdventOfCode.getResource("day3/directions")) {
            var directions = new ArrayList<Direction>();

            String line;
            while ((line = reader.readLine()) != null) {
                for (char c: line.toCharArray()) {
                    directions.add(Direction.fromChar(c));
                }
            }

            return directions;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public Grid createGrid() {
        return new Grid();
    }

    @Override public APart<?>[] parts() {
        return new APart[]{
                new Part1(this), //
                new Part2(this)
        };
    }

    @Override public int dayNumber() {
        return 3;
    }
}
