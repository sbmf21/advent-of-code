package io.frutsel_.aoc.day3;

import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;

@RequiredArgsConstructor public enum Direction {

    NORTH('^', Grid::moveUp),
    SOUTH('v', Grid::moveDown),
    EAST('>', Grid::moveRight),
    WEST('<', Grid::moveLeft);

    /**
     * <code>values()</code> always calculates the values from an enum.
     * Give the fact that enums are constant, this results in many calculations.
     */
    public static final Direction[]    CACHE = Direction.values();
    private final       char           c;
    public final        Consumer<Grid> apply;

    public static Direction fromChar(char c) {

        for (Direction direction: CACHE) {
            if (direction.c == c) {
                return direction;
            }
        }

        throw new IllegalArgumentException(String.format("'%s' is not a valid %s", c, Direction.class.getName()));
    }
}
