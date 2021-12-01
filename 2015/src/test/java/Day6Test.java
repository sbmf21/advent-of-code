import nl.sbmf21.aoc15.Aoc;
import nl.sbmf21.aoc15.days.Day6;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day6Test {

    private final Day6 day = new Day6(new Aoc(), 6);

    @Test
    public void testPart1Output() {
        assertEquals(543903, (int) day.part1());
    }

    @Test
    public void testPart2Output() {
        assertEquals(14687245, (int) day.part2());
    }
}
