import io.frutsel_.aoc.Aoc;
import io.frutsel_.aoc.days.Day3;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day3Test {

    private final Day3 day = new Day3(new Aoc());

    @Test
    public void testPart1Output() {
        assertEquals(2081, day.part1());
    }

    @Test
    public void testPart2Output() {
        assertEquals(2341, day.part2());
    }
}
