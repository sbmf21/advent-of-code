import nl.sbmf21.aoc15.Aoc;
import nl.sbmf21.aoc.common.ADay;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AocTest {

    private final Aoc aoc = new Aoc();

    @Test
    public void names() {
        for (ADay day : aoc.findDays()) {
            assertEquals(String.format("Day%s", day.getNumber()), day.getClass().getSimpleName());
        }
    }
}
