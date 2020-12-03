package io.frutsel_.aoc.day3;

import io.frutsel_.aoc.APart;
import io.frutsel_.aoc.AdventOfCode;
import io.frutsel_.aoc.IDay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day3 implements IDay {

    public ArrayList<Direction> parseDirections() {
        try (var reader = AdventOfCode.getResource("day3/directions")) {
            var directions = new ArrayList<Direction>();

            String line;
            while ((line = reader.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    directions.add(Direction.fromChar(c));
                }
            }

            return directions;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public Santa createSanta() {
        return new Santa();
    }

    public HashMap<Point, Integer> createMap(int initialPresents) {
        var map = new HashMap<Point, Integer>();

        map.put(new Point(0, 0), initialPresents);

        return map;
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
        return 3;
    }
}
