package io.frutsel_.aoc.day2;

import io.frutsel_.aoc.APart;

import java.io.IOException;

public class Part2 extends APart<Day2> {

    public Part2(Day2 day) {
        super(day);
    }

    @Override
    public String solve() throws IOException {
        var total = 0;

        for (var dimension : day.parseDimensions()) {
            total += dimension.volume() + day.smallest(dimension.perimeters());
        }

        return Integer.toString(total);
    }

    @Override
    public int partNumber() {
        return 2;
    }
}
