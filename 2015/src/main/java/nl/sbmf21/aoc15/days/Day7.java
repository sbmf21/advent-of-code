package nl.sbmf21.aoc15.days;

import nl.sbmf21.aoc.common.Day;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Day7 extends Day {

    private static final Map<String, Wire> wires = new HashMap<>();
    private static final Map<Wire, Integer> cache = new HashMap<>();
    private static final Map<String, BiFunction<Integer, Integer, Integer>> operations = new HashMap<>() {{
        put("AND", (l, r) -> l & r);
        put("OR", (l, r) -> l | r);
        put("LSHIFT", (l, r) -> l << r);
        put("RSHIFT", (l, r) -> l >> r);
    }};

    public Day7() {
        for (String line : getInput()) {
            String[] parts = line.split(" -> ", 2);
            Wire wire = new Wire(getOperation(parts[0]));
            wires.put(parts[1], wire);
        }
    }

    @Override
    public @NotNull Object part1() {
        return getWireA();
    }

    @Override
    public @NotNull Object part2() {
        wires.put("b", new Wire(new Number(getWireA())));
        cache.clear();
        return getWireA();
    }

    private int getWireA() {
        return wires.get("a").getValue();
    }

    private Operation getOperation(String line) {
        if (line.matches("\\d+|\\w+")) {
            return getOperationPart(line);
        } else if (line.matches("NOT \\w+")) {
            return new Not(new Reference(line.split(" ", 2)[1]));
        } else {
            String[] parts = line.split(" ", 3);
            return new Operator(operations.get(parts[1]), getOperationPart(parts[0]), getOperationPart(parts[2]));
        }
    }

    private Operation getOperationPart(String input) {
        return input.matches("\\d+")
            ? Number.from(input)
            : new Reference(input);
    }

    private interface Operation {
        int getValue();
    }

    private record Wire(Operation operation) implements Operation {
        @Override
        public int getValue() {
            if (!cache.containsKey(this)) {
                cache.put(this, operation.getValue());
            }
            return cache.get(this);
        }
    }

    private record Reference(String wire) implements Operation {
        @Override
        public int getValue() {
            return wires.get(wire).getValue();
        }
    }

    private record Number(int number) implements Operation {

        public static Number from(String input) {
            return new Number(Integer.parseInt(input));
        }

        @Override
        public int getValue() {
            return number;
        }
    }

    private record Not(Operation operation) implements Operation {
        @Override
        public int getValue() {
            return ~operation.getValue();
        }
    }

    private record Operator(
        BiFunction<Integer, Integer, Integer> operation,
        Operation left,
        Operation right
    ) implements Operation {
        @Override
        public int getValue() {
            return operation.apply(left.getValue(), right.getValue());
        }
    }
}
