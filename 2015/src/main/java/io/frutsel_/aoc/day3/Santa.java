package io.frutsel_.aoc.day3;

import java.util.HashMap;

public class Santa {

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


    public Point getPoint() {
        return new Point(x, y);
    }
}
