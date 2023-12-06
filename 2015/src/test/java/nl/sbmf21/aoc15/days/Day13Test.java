package nl.sbmf21.aoc15.days;

import org.junit.jupiter.api.Test;

import static nl.sbmf21.aoc.testing.UtilKt.buildPuzzle;
import static nl.sbmf21.aoc.testing.UtilKt.testDay;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day13Test {

    @Test
    public void testInput() {
        testDay(Day13.class, 618, 601, false, null);
    }

    @Test
    public void testExample() {
        assertEquals(
            330,
            buildPuzzle(Day13.class, true, null).part1()
        );
    }
}
