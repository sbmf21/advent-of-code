package $package.days;

import org.junit.jupiter.api.Test;

import static nl.sbmf21.aoc.common.UtilKt.getTODO;
import static nl.sbmf21.aoc.testing.UtilKt.testDay;

public class Day${day}Test {

    @Test
    public void testInput() {
        testDay(Day${day}.class, getTODO(), getTODO(), false, null);
    }

    @Test
    public void testExample() {
        testDay(Day${day}.class, getTODO(), getTODO(), true, null);
    }
}
