import io.frutsel_.aoc.day3.Day3;
import io.frutsel_.aoc.day3.Part1;
import io.frutsel_.aoc.day3.Part2;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day3Test {

    private Day3 day;

    @Before
    public void createDay() {
        day = new Day3();
    }

    @Test
    public void testDayNumber() {
        assertEquals(3, day.dayNumber());
    }

    @Test
    public void testPart1Output() throws IOException {
        var part = new Part1(day);

        assertEquals("2081", part.solve());
        assertEquals(1, part.partNumber());
    }

    @Test
    public void testPart2Output() throws IOException {
        var part = new Part2(day);

        assertEquals("2341", part.solve());
        assertEquals(2, part.partNumber());
    }
}
