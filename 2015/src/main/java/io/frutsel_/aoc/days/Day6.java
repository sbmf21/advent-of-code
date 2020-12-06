package io.frutsel_.aoc.days;

import io.frutsel_.aoc.Aoc;
import io.frutsel_.aoc.common.ADay;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6 extends ADay {

    private final int size = 1000;
    private final String regex = "(?<action>turn|toggle) ((?<switch>on|off) )?(?<sx>\\d+),(?<sy>\\d+) through (?<ex>\\d+),(?<ey>\\d+)";
    private final Pattern pattern = Pattern.compile(regex);

    public Day6(Aoc aoc) {
        super(aoc);
    }

    @Override
    public int number() {
        return 6;
    }

    @Override
    public int part1() {
        return countLit(applyGrid(() -> {
            var grid = new Boolean[size][size];
            for (var booleans : grid) Arrays.fill(booleans, Boolean.FALSE);
            return grid;
        }, (matcher, grid, x, y) -> switch (matcher.group("action")) {
            case "turn" -> matcher.group("switch").equals("on");
            default -> !grid[x][y];
        }));
    }

    private int countLit(Boolean[][] grid) {
        var count = 0;

        for (var column : grid) {
            for (boolean lit : column) {
                if (lit) {
                    count++;
                }
            }
        }

        return count;
    }

    @Override
    public int part2() {
        return countBrightness(applyGrid(() -> {
            var grid = new Integer[size][size];
            for (var ints : grid) Arrays.fill(ints, 0);
            return grid;
        }, (matcher, grid, x, y) -> Math.max(0, grid[x][y] + switch (matcher.group("action")) {
            case "turn" -> matcher.group("switch").equals("on") ? 1 : -1;
            default -> 2;
        })));
    }

    private int countBrightness(Integer[][] grid) {
        int brightness = 0;

        for (var column : grid) {
            for (int bright : column) {
                brightness += bright;
            }
        }

        return brightness;
    }

    private <T> T[][] applyGrid(Grid<T> create, Apply<T> function) {
        var grid = create.apply();

        for (var line : getInput()) {
            var m = pattern.matcher(line);

            if (!m.matches()) {
                throw new IllegalArgumentException(String.format("Line %s is invalid", line));
            }

            for (int x = sx(m); x <= ex(m); x++) {
                for (int y = sy(m); y <= ey(m); y++) {
                    grid[x][y] = function.apply(m, grid, x, y);
                }
            }
        }

        return grid;
    }

    private int intFrom(Matcher matcher, String group) {
        return Integer.parseInt(matcher.group(group));
    }

    private int sx(Matcher matcher) {
        return intFrom(matcher, "sx");
    }

    private int sy(Matcher matcher) {
        return intFrom(matcher, "sy");
    }

    private int ex(Matcher matcher) {
        return intFrom(matcher, "ex");
    }

    private int ey(Matcher matcher) {
        return intFrom(matcher, "ey");
    }
}


@FunctionalInterface
interface Grid<T> {
    T[][] apply();
}

@FunctionalInterface
interface Apply<T> {
    T apply(Matcher matcher, T[][] grid, int x, int y);
}
