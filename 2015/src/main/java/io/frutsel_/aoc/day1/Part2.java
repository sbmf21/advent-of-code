package io.frutsel_.aoc.day1;

import io.frutsel_.aoc.APart;

public class Part2 extends APart<Day1> {

    public Part2(Day1 day) {
        super(day);
    }

    @Override public String solve() {
        return findFloor(day.loadInstructions());
    }

    private String findFloor(char[] chars) {
        int floor = 0;

        for (int i = 0; i < chars.length; ) {
            floor += getDirection(chars[i++]);

            if (isFinalFloor(floor)) {
                return Integer.toString(i);
            }
        }

        return "404: Not Found";
    }

    private boolean isFinalFloor(int floor) {
        return floor == -1;
    }

    private int getDirection(char c) {
        return switch (c) {
            case '(' -> 1;
            case ')' -> -1;
            default -> 0;
        };
    }

    @Override public int partNumber() {
        return 2;
    }
}