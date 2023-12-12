package nl.sbmf21.aoc15.days;

import nl.sbmf21.aoc.common.Day;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Day14 extends Day {

    public static final int DEFAULT_REINDEER_SCORE_TIME = 2503;
    public static int REINDEER_SCORE_TIME = DEFAULT_REINDEER_SCORE_TIME;

    private final List<Reindeer> travels;

    public Day14() {
        this.travels = getInput().stream()
            .map((line) -> {
                String[] parts = line.split(" ");
                return new Reindeer(
                    Integer.parseInt(parts[3]),
                    Integer.parseInt(parts[6]),
                    Integer.parseInt(parts[13])
                );
            })
            .toList();

        for (int i = 0; i < REINDEER_SCORE_TIME; i++) {
            travels.forEach(Reindeer::step);
            travels.forEach((t) -> t.score(travels));
        }
    }

    @Override
    public @NotNull Object part1() {
        return travels.stream()
            .map(Reindeer::getDistance)
            .max(Integer::compare)
            .orElse(-1);
    }

    @Override
    public @NotNull Object part2() {
        return travels.stream()
            .map(Reindeer::getScore)
            .max(Integer::compare)
            .orElse(-1);
    }

    private static class Reindeer {

        private final int speed;
        private final int flyTime;
        private final int restTime;

        private boolean flying = true;
        private int distance = 0;
        private int step = 0;
        private int score = 0;

        public Reindeer(int speed, int flyTime, int restTime) {
            this.speed = speed;
            this.flyTime = flyTime;
            this.restTime = restTime;
        }

        public void step() {
            step++;
            if (flying) {
                distance += speed;
                if (step == flyTime) {
                    flying = false;
                    step = 0;
                }
            } else if (step == restTime) {
                flying = true;
                step = 0;
            }
        }

        public void score(List<Reindeer> travels) {
            if (distance == travels.stream().map(Reindeer::getDistance).max(Integer::compare).orElse(-1)) {
                score++;
            }
        }

        public int getScore() {
            return score;
        }

        public int getDistance() {
            return distance;
        }
    }
}
