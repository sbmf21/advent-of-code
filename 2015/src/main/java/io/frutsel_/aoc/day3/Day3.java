package io.frutsel_.aoc.day3;

import io.frutsel_.aoc.ADay;
import io.frutsel_.aoc.APart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Day3 extends ADay {

    public ArrayList<Direction> parseDirections() throws IOException {
        try (var reader = getResource()) {
            var directions = new ArrayList<Direction>();

            String line;
            while ((line = reader.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    directions.add(Direction.fromChar(c));
                }
            }

            return directions;
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
