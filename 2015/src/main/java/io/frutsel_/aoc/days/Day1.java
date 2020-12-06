package io.frutsel_.aoc.days;

import io.frutsel_.aoc.Aoc;
import io.frutsel_.aoc.common.ADay;

import java.util.HashMap;

public class Day1 extends ADay {

    private final char[] instructions = parseInstructions();

    public Day1(Aoc aoc) {
        super(aoc);
    }

    @Override
    public int number() {
        return 1;
    }

    @Override
    public int part1() {
        var map = countChars();

        return map.getOrDefault('(', 0) //
                - map.getOrDefault(')', 0);
    }

    @Override
    public int part2() {
        return findFloor();
    }

    private int findFloor() {
        int floor = 0;

        for (int i = 0; i < instructions.length; ) {
            if (isFinalFloor(floor += getDirection(instructions[i++]))) {
                return i;
            }
        }

        return 0;
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

    private HashMap<Character, Integer> countChars() {
        var map = new HashMap<Character, Integer>();

        for (var c : instructions) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
            } else {
                map.replace(c, map.get(c) + 1);
            }
        }

        return map;
    }

    private char[] parseInstructions() {

        StringBuilder input = new StringBuilder();
        this.getInput().forEach(input::append);

        return input.toString().toCharArray();
    }
}
