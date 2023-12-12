package nl.sbmf21.aoc15.days;

import com.google.gson.GsonBuilder;
import nl.sbmf21.aoc.common.Day;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class Day12 extends Day {

    private final Object input = new GsonBuilder().create().fromJson(getInput().get(0), Object.class);

    @Override
    public @NotNull Object part1() {
        return getValue(input, false);
    }

    @Override
    public @NotNull Object part2() {
        return getValue(input, true);
    }

    private int getValue(Object object, boolean ignoreRed) {
        if (object instanceof List<?> list) return list.stream()
            .map((v) -> getValue(v, ignoreRed))
            .reduce(0, Integer::sum);
        else if (object instanceof Map<?, ?> map) return ignoreRed && hasRed(map) ? 0 : map.values().stream()
            .map((v) -> getValue(v, ignoreRed))
            .reduce(0, Integer::sum);
        else if (object instanceof Double) return ((Double) object).intValue();
        else if (object instanceof String) return 0;
        else throw new RuntimeException("Unrecognized type: " + object.getClass() + " " + object);
    }

    private boolean hasRed(Map<?, ?> map) {
        return map.containsKey("red")
            || map.containsValue("red");
    }
}
