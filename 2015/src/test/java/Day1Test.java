import nl.sbmf21.aoc15.Aoc;
import nl.sbmf21.aoc15.days.Day1;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day1Test {

    private final Day1 day = new Day1(new Aoc());

    @Test
    public void testPart1Output() {
        assertEquals(138, (int) day.part1());
    }

    @Test
    public void testPart2Output() {
        assertEquals(1771, (int) day.part2());
    }
}
