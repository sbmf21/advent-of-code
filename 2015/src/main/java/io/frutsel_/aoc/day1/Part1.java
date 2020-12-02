package io.frutsel_.aoc.day1;

import io.frutsel_.aoc.APart;

import java.util.HashMap;

public class Part1 extends APart<Day1> {

    public Part1(Day1 day) {
        super(day);
    }

    @Override public void solve() {
        var chars = day.loadInstructions();
        var map   = countChars(chars);

        var up    = map.getOrDefault('(', 0);
        var down  = map.getOrDefault(')', 0);
        var floor = up - down;

        System.out.println(floor);
    }

    private HashMap<Character, Integer> countChars(char[] chars) {
        var map = new HashMap<Character, Integer>();

        for (var c: chars) {
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }

            map.replace(c, map.get(c) + 1);
        }

        return map;
    }

    @Override public int partNumber() {
        return 1;
    }
}
