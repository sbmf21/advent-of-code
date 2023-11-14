package nl.sbmf21.aoc15.days;

import nl.sbmf21.aoc.common.Day;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Day9 extends Day {

    private final List<Location> locations;
    private final List<Route> routes;

    public Day9() {
        Map<String, Location> locations = new HashMap<>();

        getInput().forEach((line) -> {
            String[] parts = line.split(" ", 5);
            int distance = Integer.parseInt(parts[4]);

            Location first = locations.computeIfAbsent(parts[0], s -> new Location());
            Location second = locations.computeIfAbsent(parts[2], s -> new Location());

            first.destinations.put(second, distance);
            second.destinations.put(first, distance);
        });

        this.locations = locations.values().stream().toList();

        this.routes = new ArrayList<>();
        this.locations.forEach(location -> routes.addAll(getRoute(this.locations, location, new Route(0))));
    }

    @Override
    public @NotNull Object part1() {
        return routes.stream()
            .min(Comparator.comparing(Route::getDistance))
            .map(route -> route.distance)
            .orElse(-1);
    }

    @Override
    public @NotNull Object part2() {
        return routes.stream()
            .max(Comparator.comparing(Route::getDistance))
            .map(route -> route.distance)
            .orElse(-1);
    }

    private List<Route> getRoute(List<Location> locations, Location location, Route route) {

        List<Route> routes = new ArrayList<>();
        List<Location> possibleLocations = locations.stream().filter(option -> option != location).toList();

        if (possibleLocations.isEmpty()) routes.add(route);
        else possibleLocations.forEach(destination -> {
            int distance = location.destinations.get(destination);
            routes.addAll(getRoute(possibleLocations, destination, route.next(distance)));
        });

        return routes;
    }

    private static class Location {

        public final Map<Location, Integer> destinations = new HashMap<>();
    }

    private static class Route {

        public int distance;

        public Route(int distance) {
            this.distance = distance;
        }

        public int getDistance() {
            return distance;
        }

        public Route next(int distance) {
            return new Route(this.distance + distance);
        }
    }
}
