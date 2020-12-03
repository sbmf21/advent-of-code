import io.frutsel_.aoc.day1.Day1;
import io.frutsel_.aoc.day1.Part1;
import io.frutsel_.aoc.day1.Part2;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day1Test {

    private Day1 day;

    @Before
    public void createDay() {
        day = new Day1();
    }

    @Test
    public void testDayNumber() {
        assertEquals(1, day.dayNumber());
    }

    @Test
    public void testPart1Output() throws IOException {
        var part = new Part1(day);

        assertEquals("138", part.solve());
        assertEquals(1, part.partNumber());
    }

    @Test
    public void testPart2Output() throws IOException {
        var part = new Part2(day);

        assertEquals("1771", part.solve());
        assertEquals(2, part.partNumber());
    }
}
