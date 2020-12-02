package io.frutsel_.aoc.day1;

import io.frutsel_.aoc.APart;

public class Part2 extends APart<Day1> {

    public Part2(Day1 day) {
        super(day);
    }

    @Override public void solve() {
        var chars = day.loadInstructions();

        int floor = 0;

        for (int i = 0; i < chars.length; ) {
            floor += getDirection(chars[i++]);

            if (floor == -1) {
                System.out.println(i);
                break;
            }
        }
    }

    private int getDirection(char c) {
        return switch (c) {
            case '(' -> 1;
            case ')' -> -1;
            default -> 0;
        };
    }

    @Override public int partNumber() {
        return 0;
    }
}
