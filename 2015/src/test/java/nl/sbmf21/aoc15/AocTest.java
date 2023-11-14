package nl.sbmf21.aoc15;

import nl.sbmf21.aoc.common.Day;
import nl.sbmf21.aoc.common.DayMeta;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AocTest {

    public <T extends Day, A, B> void testDay(Class<T> clazz, A part1, B part2) {
        testDay(clazz, part1, part2, false, null);
    }

    public <T extends Day, A, B> void testDay(Class<T> clazz, A part1, B part2, Boolean example) {
        testDay(clazz, part1, part2, example, null);
    }

    public <T extends Day, A, B> void testDay(Class<T> clazz, A part1, B part2, Boolean example, String filename) {
        var day = buildDay(clazz, example, filename);

        assertEquals(part1, day.part1());
        assertEquals(part2, day.part2());
    }

    public <T extends Day> T buildDay(Class<T> clazz) {
        return buildDay(clazz, false, null);
    }

    public <T extends Day> T buildDay(Class<T> clazz, Boolean example, String filename) {
        return new DayMeta<>(clazz).build(example, filename);
    }
}
