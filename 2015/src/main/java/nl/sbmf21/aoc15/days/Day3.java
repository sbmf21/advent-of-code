package nl.sbmf21.aoc15.days;

import nl.sbmf21.aoc.common.ADay;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;

public class Day3 extends ADay {

    private final ArrayList<Direction> directions = parseDirections();

    public Day3(List<String> input) {
        super(input);
    }

    @Override
    public @NotNull Integer part1() {
        var presents = createMap(1);
        var santa = new Santa();

        directions.forEach(direction -> santa.deliverPresent(direction, presents));

        return presents.size();
    }

    @Override
    public @NotNull Integer part2() {
        var presents = createMap(2);

        move(presents, new Santa(), new Santa());

        return presents.size();
    }

    private void move(HashMap<Point, Integer> presents, Santa santa, Santa robotSanta) {
        var iterator = directions.iterator();

        while (iterator.hasNext()) {
            santa.deliverPresent(iterator, presents);

            if (!iterator.hasNext()) {
                break;
            }

            robotSanta.deliverPresent(iterator, presents);
        }
    }

    public HashMap<Point, Integer> createMap(int initialPresents) {
        var map = new HashMap<Point, Integer>();

        map.put(new Point(0, 0), initialPresents);

        return map;
    }

    private ArrayList<Direction> parseDirections() {
        var directions = new ArrayList<Direction>();

        for (var line : getInput()) {
            for (char c : line.toCharArray()) {
                directions.add(Direction.fromChar(c));
            }
        }

        return directions;
    }

    public enum Direction {

        NORTH('^', Santa::moveUp),
        SOUTH('v', Santa::moveDown),
        EAST('>', Santa::moveRight),
        WEST('<', Santa::moveLeft);

        /**
         * <code>values()</code> always calculates the values from an enum.
         * Give the fact that enums are constant, this results in many calculations.
         */
        public static final Direction[] CACHE = Direction.values();
        private final char key;
        public final Consumer<Santa> move;

        Direction(char key, Consumer<Santa> move) {
            this.key = key;
            this.move = move;
        }

        public static Direction fromChar(char c) {

            for (Direction direction : CACHE) {
                if (direction.key == c) {
                    return direction;
                }
            }

            throw new IllegalArgumentException(String.format("'%s' is not a valid %s", c, Direction.class.getName()));
        }
    }

    public static class Santa {

        private int x = 0, y = 0;

        public void moveUp() {
            x++;
        }

        public void moveDown() {
            x--;
        }

        public void moveRight() {
            y++;
        }

        public void moveLeft() {
            y--;
        }

        public void deliverPresent(Direction direction, HashMap<Point, Integer> presents) {

            direction.move.accept(this);
            var point = getPoint();

            if (!presents.containsKey(point)) {
                presents.put(point, 1);
            } else {
                presents.replace(point, presents.get(point) + 1);
            }
        }

        public void deliverPresent(Iterator<Direction> directions, HashMap<Point, Integer> presents) {
            deliverPresent(directions.next(), presents);
        }

        public Point getPoint() {
            return new Point(x, y);
        }
    }

    public static class Point {

        private final int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Point house = (Point) o;

            return x == house.x
                && y == house.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return String.format("House{x=%d, y=%d}", x, y);
        }
    }
}
