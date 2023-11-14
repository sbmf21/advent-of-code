package nl.sbmf21.aoc15.days;

import nl.sbmf21.aoc15.AocTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day13Test extends AocTest {

    @Test
    public void testInput() {
        testDay(Day13.class, 618, 601);
    }

    @Test
    public void testExample() {
        assertEquals(330, buildDay(Day13.class, true).part1());
    }
}
