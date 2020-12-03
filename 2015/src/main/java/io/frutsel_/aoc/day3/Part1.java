package io.frutsel_.aoc.day3;

import io.frutsel_.aoc.APart;

import java.util.HashMap;

public class Part1 extends APart<Day3> {

    public Part1(Day3 day) {
        super(day);
    }

    @Override public String solve() {

        var directions = day.parseDirections();
        var grid       = day.createGrid();

        var houses = new HashMap<House, Integer>();
        houses.put(new House(0, 0), 1);

        for (Direction direction: directions) {
            direction.apply.accept(grid);
            var house = grid.getHouse();

            if (!houses.containsKey(house)) {
                houses.put(house, 1);
            } else {
                houses.replace(house, houses.get(house) + 1);
            }
        }

        return Integer.toString(houses.size());
    }

    @Override public int partNumber() {
        return 1;
    }
}
