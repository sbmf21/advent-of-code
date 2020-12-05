import io.frutsel_.aoc.Aoc;
import io.frutsel_.aoc.days.Day4;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day4Test {

    private final Day4 day;

    public Day4Test() throws Exception {
        day = new Day4(new Aoc());
    }

    @Test
    public void testPart1Output() {
        assertEquals(117946, day.part1());
    }

    @Test
    public void testPart2Output() {
        assertEquals(3938038, day.part2());
    }
}
