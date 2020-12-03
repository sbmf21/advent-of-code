package io.frutsel_.aoc.day3;

public class Grid {

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

    public House getHouse() {
        return new House(x, y);
    }
}
