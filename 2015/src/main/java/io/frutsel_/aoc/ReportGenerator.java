package io.frutsel_.aoc;

import io.frutsel_.aoc.common.ADay;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

public class ReportGenerator {
    private static final ArrayList<Timing> timings = new ArrayList<>();

    public static void add(ADay day, long start, long mid, long end, int part1, int part2) {
        timings.add(new Timing(day, start, mid, end, part1, part2));
    }

    public static void print() {

        var mdlen = "Day".length();
        var mtlen = "Total".length();
        var mp1len = "Part 1".length();
        var mp2len = "Part 2".length();
        var mp1alen = "Part 1".length();
        var mp2alen = "Part 2".length();

        for (var timing : timings) {
            var dlen = Integer.toString(timing.day).length();
            var tlen = timing.timeTotal.length();
            var p1len = timing.timePart1.length();
            var p2len = timing.timePart2.length();
            var p1alen = Integer.toString(timing.part1).length();
            var p2alen = Integer.toString(timing.part2).length();

            if (dlen > mdlen) mdlen = dlen;
            if (tlen > mtlen) mtlen = tlen;
            if (p1len > mp1len) mp1len = p1len;
            if (p2len > mp2len) mp2len = p2len;
            if (p1alen > mp1alen) mp1alen = p1alen;
            if (p2alen > mp2alen) mp2alen = p2alen;
        }

        var builder = new StringBuilder();

        var htlen = mtlen + mp1len + mp2len + 6;
        var halen = mp1alen + mp2alen + 3;

        var hsize = new ArrayList<Integer>();
        var hlen = mdlen + htlen + halen + 6;
        hsize.add(hlen);

        line(builder, hsize);

        var aoc = "Advent of Code 2015";
        var aoclen = aoc.length();
        var aocdif = hlen - aoclen;
        int aocl = aocdif / 2;
        int aocr = aocdif - aocl;

        builder.append("| ").append(" ".repeat(aocl)).append(aoc).append(" ".repeat(aocr)).append(" |").append('\n');

        var size = new ArrayList<Integer>();
        size.add(mdlen);
        size.add(htlen);
        size.add(halen);

        var head = new ArrayList<Map>();
        head.add(new Map("Day", mdlen));
        head.add(new Map("Timings", htlen));
        head.add(new Map("Answers", halen));

        line(builder, size);
        fill(builder, head, true);

        var sizes = new ArrayList<Integer>();
        sizes.add(mdlen);
        sizes.add(mtlen);
        sizes.add(mp1len);
        sizes.add(mp2len);
        sizes.add(mp1alen);
        sizes.add(mp2alen);

        var header = new ArrayList<Map>();
        header.add(new Map("Day", mdlen));
        header.add(new Map("Total", mtlen));
        header.add(new Map("Part 1", mp1len));
        header.add(new Map("Part 2", mp2len));
        header.add(new Map("Part 1", mp1alen));
        header.add(new Map("Part 2", mp2alen));

        line(builder, sizes);
        fill(builder, header, true);
        line(builder, sizes);

        for (var timing : timings) {
            var maps = new ArrayList<Map>();
            maps.add(new Map(Integer.toString(timing.day), mdlen));
            maps.add(new Map(timing.timeTotal, mtlen));
            maps.add(new Map(timing.timePart1, mp1len));
            maps.add(new Map(timing.timePart2, mp2len));
            maps.add(new Map(Integer.toString(timing.part1), mp1alen));
            maps.add(new Map(Integer.toString(timing.part2), mp2alen));

            fill(builder, maps, false);
        }
        line(builder, sizes);

        System.out.print(builder);
    }

    private static void fill(StringBuilder builder, ArrayList<Map> maps, boolean left) {
        var parts = new ArrayList<String>();

        for (var map : maps) {
            var spaces = " ".repeat(map.length - map.value.length());
            var value = map.value;

            if (left) {
                parts.add(String.format("%s%s", value, spaces));
            } else {
                parts.add(String.format("%s%s", spaces, value));
            }
        }

        builder.append("| ").append(String.join(" | ", parts)).append(" |").append('\n');
    }

    private static void line(StringBuilder builder, ArrayList<Integer> sizes) {
        var parts = new ArrayList<String>();

        for (var map : sizes) {
            parts.add(String.format("%s", "-".repeat(map)));
        }

        builder.append("+-").append(String.join("-+-", parts)).append("-+").append('\n');
    }
}

@RequiredArgsConstructor
class Map {
    final String value;
    final int length;
}

class Timing {

    final int day;
    final String timeTotal;
    final String timePart1;
    final String timePart2;

    final int part1;
    final int part2;

    Timing(ADay day, long start, long mid, long end, int part1, int part2) {
        this.day = day.number();

        this.timeTotal = timeString(end - start);
        this.timePart1 = timeString(mid - start);
        this.timePart2 = timeString(end - mid);

        this.part1 = part1;
        this.part2 = part2;
    }

    String timeString(long time) {
        if (time > 1000) {
            return (time / 1000d) + "  s";
        } else {
            return time + " ms";
        }
    }
}