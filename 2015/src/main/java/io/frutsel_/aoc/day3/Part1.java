package io.frutsel_.aoc.day3;

import io.frutsel_.aoc.APart;

import java.io.IOException;
import java.util.HashMap;

public class Part1 extends APart<Day3> {

    public Part1(Day3 day) {
        super(day);
    }

    @Override
    public String solve() throws IOException {
        var presents = day.createMap(1);

        move(presents, day.createSanta());

        return Integer.toString(presents.size());
    }

    private void move(HashMap<Point, Integer> presents, Santa santa) throws IOException {
        for (Direction direction : day.parseDirections()) {
            santa.deliverPresent(direction, presents);
        }
    }

    @Override
    public int partNumber() {
        return 1;
    }
}
