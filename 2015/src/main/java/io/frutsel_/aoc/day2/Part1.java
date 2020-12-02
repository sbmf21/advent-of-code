package io.frutsel_.aoc.day2;

import io.frutsel_.aoc.APart;

public class Part1 extends APart<Day2> {

    public Part1(Day2 day) {
        super(day);
    }

    @Override public void solve() {
        var total = 0;

        for (var dimension: day.parseDimensions()) {
            var smallest = day.smallest(dimension.sides());
            total += dimension.size() + smallest;
        }

        System.out.println(total);
    }

    @Override public int partNumber() {
        return 1;
    }
}
