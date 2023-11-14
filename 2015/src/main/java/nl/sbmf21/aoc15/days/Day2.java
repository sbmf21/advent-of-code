package nl.sbmf21.aoc15.days;

import nl.sbmf21.aoc.common.ADay;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Day2 extends ADay {

    private final ArrayList<Dimension> dimensions = parseDimensions();

    @Override
    public @NotNull Integer part1() {
        var total = 0;

        for (var dimension : dimensions) {
            total += dimension.size() + smallest(dimension.sides());
        }

        return total;
    }

    @Override
    public @NotNull Integer part2() {
        var total = 0;

        for (var dimension : dimensions) {
            total += dimension.volume() + smallest(dimension.perimeters());
        }

        return total;
    }

    public ArrayList<Dimension> parseDimensions() {
        var dimensions = new ArrayList<Dimension>();

        getInput().forEach(line -> dimensions.add(Dimension.fromLine(line)));

        return dimensions;
    }

    public int smallest(int[] values) {
        var smallest = values[0];

        for (int i = 1; i < values.length; i++) {
            var value = values[i];

            if (value < smallest) {
                smallest = value;
            }
        }

        return smallest;
    }

    public static class Dimension {

        public final int l, w, h;

        public static Dimension fromLine(String line) {
            var parts = line.split("x");

            return new Dimension(
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
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
                l * w,
                w * h,
                h * l
            };
        }

        public int[] perimeters() {
            return new int[]{
                2 * (l + w),
                2 * (w + h),
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
}
