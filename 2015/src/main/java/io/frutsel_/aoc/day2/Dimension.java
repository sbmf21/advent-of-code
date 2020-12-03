package io.frutsel_.aoc.day2;

public class Dimension {

    public final int l, w, h;

    public static Dimension fromLine(String line) {
        var parts = line.split("x");

        return new Dimension(
                Integer.parseInt(parts[0]), //
                Integer.parseInt(parts[1]), //
                Integer.parseInt(parts[2])
        );
    }

    private Dimension(int l, int w, int h) {
        this.l = l;
        this.w = w;
        this.h = h;
    }

    public int[] sides() {
        return new int[]{
                l * w, //
                w * h, //
                h * l
        };
    }

    public int[] perimeters() {
        return new int[]{
                2 * (l + w), //
                2 * (w + h), //
                2 * (h + l)
        };
    }

    public int volume() {
        return l * w * h;
    }

    public int size() {
        var size = 0;

        for (var side : sides()) {
            size += side * 2;
        }

        return size;
    }
}
