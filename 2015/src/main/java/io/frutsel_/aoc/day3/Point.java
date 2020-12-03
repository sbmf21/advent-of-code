package io.frutsel_.aoc.day3;

import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor public class Point {

    private final int x, y;

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Point house = (Point) o;

        return x == house.x && y == house.y;
    }

    @Override public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override public String toString() {
        return String.format("House{x=%d, y=%d}", x, y);
    }
}
