package com.adventofcode.year2016.day11;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Floorstate {
    private static final Map<Floor, Floorstate> empties = new HashMap<>();

    static {
        for (Floor floor : Floor.values()) {
            empties.put(floor, new Floorstate(floor, Machines.empty()));
        }
    }

    private final Floor floor;
    private final Machines machines;

    private Floorstate(Floor floor, Machines machines) {
        this.floor = Objects.requireNonNull(floor);
        this.machines = Objects.requireNonNull(machines);
    }

    public static Floorstate empty(Floor floor) {
        return empties.get(floor);
    }

    public static Floorstate create(Floor floor, MachineDefinition[] machineDefinitions, Set<Machine> machines) {
        return new Floorstate(floor, Machines.valueOf(machines.stream()
                .map(m -> {
                    for (MachineDefinition definition : machineDefinitions) {
                        if (definition.getMachine().equals(m)) {
                            return definition.getId();
                        }
                    }
                    throw new IllegalArgumentException();
                })
                .reduce(0, (a, b) -> a | b)));
    }

    public static Floorstate create(Floor floor, Machines machines) {
        if (machines.isEmpty()) {
            return empty(floor);
        }
        return new Floorstate(floor, machines);
    }


    public Floor getFloor() {
        return floor;
    }

    public Set<Machines> getPossibleMoves() {
        Set<Machines> payloads = new HashSet<>();
        payloads.addAll(machines.getUnigrams());
        payloads.addAll(machines.getBigrams());
        return payloads;
    }

    // States are invalid when:
    //  Chip is with invalid Generator (unless with compatible Generator)
    public boolean isValid(MachineDefinition[] machineDefinitions) {
        if (machines.isEmpty()) {
            return true;
        }

        Set<Machine> generators = Stream.of(machineDefinitions)
                .filter(md -> machines.has(Machines.valueOf(md.getId())))
                .filter(machineDefinition -> machineDefinition.getMachine() instanceof Generator)
                .map(MachineDefinition::getMachine)
                .collect(Collectors.toSet());

        if (generators.isEmpty()) {
            return true;
        }

        return Stream.of(machineDefinitions)
                .filter(md -> machines.has(Machines.valueOf(md.getId())))
                .filter(machineDefinition -> machineDefinition.getMachine() instanceof Microchip)
                .map(MachineDefinition::getMachine)
                .allMatch(m -> generators.contains(Generator.valueOf(m.getType())));
    }

    public Floorstate withoutMachines(Machines machinesToRemove) {
        return Floorstate.create(floor, machines.without(machinesToRemove));
    }

    public Floorstate withMachines(Machines machinesToAdd) {
        return Floorstate.create(floor, machines.with(machinesToAdd));
    }

    public Machines getMachines() {
        return machines;
    }

    public boolean hasMachines(Machines search) {
        return machines.has(search);
    }

    @Override
    public String toString() {
        return "Floorstate{" +
                "floor=" + floor +
                ", machines=" + machines +
                '}';
    }
}
