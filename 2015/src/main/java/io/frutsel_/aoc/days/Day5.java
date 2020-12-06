package io.frutsel_.aoc.days;

import io.frutsel_.aoc.ADay;
import io.frutsel_.aoc.Aoc;

public class Day5 extends ADay {

    public Day5(Aoc aoc) {
        super(aoc);
    }

    @Override
    public int number() {
        return 5;
    }

    @Override
    public int part1() {

        int count = 0;

        var nono = new String[]{
                "ab", "cd", "pq", "xy"
        };

        for (String line : input) {
            if (line.matches(".*[aeiou].*[aeiou].*[aeiou].*")) {
                a:
                for (char c : line.toCharArray()) {
                    if (line.contains(String.format("%s", c).repeat(2))) {
                        for (String no : nono) {
                            if (line.contains(no)) {
                                break a;
                            }
                        }

                        count++;
                        break;
                    }
                }
            }
        }

        return count;
    }

    @Override
    public int part2() {
        int count = 0;

        for (String line : input) {
            var m1 = false;

            for (int i = 0; i < line.length() - 1; i++) {
                String sub = line.substring(i, i + 2);
                String match = String.format(".*%1$s.*%1$s.*", sub);

                if (line.matches(match)) {
                    m1 = true;
                    break;
                }
            }

            if (!m1) {
                continue;
            }

            for (char c : line.toCharArray()) {
                String match = String.format(".*%1$s.%1$s.*", c);
                if (line.matches(match)) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }
}
