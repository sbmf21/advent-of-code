import nl.sbmf21.aoc15.Aoc;
import nl.sbmf21.aoc15.days.Day5;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day5Test {

    private final Day5 day = new Day5(new Aoc());

    @Test
    public void testPart1Output() {
        assertEquals(258, (int) day.part1());
    }

    @Test
    public void testPart2Output() {
        assertEquals(53, (int) day.part2());
    }
}
