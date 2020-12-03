package io.frutsel_.aoc.day3;

import io.frutsel_.aoc.APart;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class Part2 extends APart<Day3> {

    public Part2(Day3 day) {
        super(day);
    }

    @Override
    public String solve() throws IOException {

        Santa santa = day.createSanta(), robotSanta = day.createSanta();
        var directions = day.parseDirections().iterator();
        var presents = day.createMap(2);

        move(directions, presents, santa, robotSanta);

        return Integer.toString(presents.size());
    }

    private void move(Iterator<Direction> directions, HashMap<Point, Integer> presents, Santa santa, Santa robotSanta) {
        while (directions.hasNext()) {
            santa.deliverPresent(directions.next(), presents);

            if (!directions.hasNext()) {
                break;
            }

            robotSanta.deliverPresent(directions.next(), presents);
        }
    }

    @Override
    public int partNumber() {
        return 2;
    }
}
