package nl.sbmf21.aoc15.days;

import nl.sbmf21.aoc.common.Day;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Day13 extends Day {

    private final Map<String, PersonData> people;

    public Day13() {
        Map<String, PersonData> people = new HashMap<>();
        for (String line : getInput()) {
            String[] parts = line.substring(0, line.length() - 1).split(" ");
            PersonData data = people.computeIfAbsent(parts[0], (s) -> new PersonData());
            data.map.put(parts[10], new SeatData(
                Objects.equals(parts[2], "gain"),
                Integer.parseInt(parts[3]))
            );
        }

        this.people = people;
    }

    @Override
    public @NotNull Object part1() {
        return permutations(new ArrayList<>(people.keySet())).stream()
            .map((list) -> {
                int totalChange = 0;
                for (int i = 0; i < list.size(); i++) {
                    int previous = (i - 1 == -1) ? list.size() - 1 : i - 1;
                    int next = i + 1 == list.size() ? 0 : i + 1;

                    PersonData data = people.get(list.get(i));
                    totalChange = data.map.get(list.get(previous)).apply(totalChange);
                    totalChange = data.map.get(list.get(next)).apply(totalChange);
                }

                return totalChange;
            })
            .max(Integer::compare)
            .orElse(-1);
    }

    @Override
    public @NotNull Object part2() {
        PersonData you = new PersonData();
        for (String name : people.keySet()) {
            you.map.put(name, new SeatData(true, 0));
            people.get(name).map.put("you", new SeatData(true, 0));
        }

        people.put("you", you);
        return part1();
    }

    private List<List<String>> permutations(List<String> input) {
        List<List<String>> solutions = new ArrayList<>();
        permutationsRecursive(input, 0, solutions);
        return solutions;
    }

    private void permutationsRecursive(List<String> input, int index, List<List<String>> output) {
        if (index == input.size()) output.add(new ArrayList<>(input));
        for (int i = index; i < input.size(); i++) {
            Collections.swap(input, index, i);
            permutationsRecursive(input, index + 1, output);
            Collections.swap(input, i, index);
        }
    }

    private static class PersonData {
        public final Map<String, SeatData> map = new HashMap<>();
    }

    private record SeatData(boolean gain, int amount) {
        public int apply(int input) {
            return gain
                ? input + amount
                : input - amount;
        }
    }
}
