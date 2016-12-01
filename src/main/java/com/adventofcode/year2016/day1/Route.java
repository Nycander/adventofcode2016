package com.adventofcode.year2016.day1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Route {
    private static final Direction STARTING_DIRECTION = Direction.NORTH;

    private final List<Location> locations;

    public Route(List<Location> locations) {
        this.locations = Objects.requireNonNull(Collections.unmodifiableList(new ArrayList<>(locations)));
    }

    public static Route fromInstructions(List<Instruction> instructions) {
        return new Route(findLocations(instructions));
    }

    public static Route parse(String potentialRoute) {
        return Route.fromInstructions(Stream.of(potentialRoute.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Instruction::valueOf)
                .collect(Collectors.toList()));
    }

    private static List<Location> findLocations(List<Instruction> instructions) {
        List<Location> locations = new ArrayList<>(instructions.size() + 1);

        Location previousLocation = Location.ORIGO;
        locations.add(previousLocation);

        Direction direction = STARTING_DIRECTION;
        for (Instruction instruction : instructions) {
            direction = direction.turn(instruction.getTurn());

            for (int i = 0; i < instruction.getLength(); i++) {
                Location currentLocation = previousLocation.asMovedBy(direction);
                locations.add(currentLocation);
                previousLocation = currentLocation;
            }
        }
        return locations;
    }

    public int getTotalLength() {
        return locations.get(locations.size() - 1).getLength();
    }

    public Optional<Integer> getLengthToFirstRevisit() {
        Set<Location> visits = new HashSet<>();
        for (Location location : locations) {
            if (visits.contains(location)) {
                return Optional.of(location.getLength());
            }
            visits.add(location);
        }
        return Optional.empty();
    }
}
