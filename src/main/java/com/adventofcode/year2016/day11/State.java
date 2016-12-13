package com.adventofcode.year2016.day11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

class State {
    private final Floor elevatorPosition;
    private final Map<Floor, Floorstate> floorstates;

    public State(Floor elevatorPosition, Map<Floor, Floorstate> floorstates) {
        this.elevatorPosition = elevatorPosition;
        this.floorstates = Collections.unmodifiableMap(floorstates);
    }

    public List<State> getPossibleNextStates() {
        List<State> possibleNextStates = new ArrayList<>();
        if (elevatorPosition.canMoveUp()) {
            possibleNextStates.addAll(getStatesTowardsTarget(elevatorPosition.moveUp()));
        }

        if (elevatorPosition.canMoveDown()) {
            possibleNextStates.addAll(getStatesTowardsTarget(elevatorPosition.moveDown()));
        }
        return possibleNextStates;
    }

    private List<State> getStatesTowardsTarget(Floor targetFloor) {
        Floorstate elevatorFloorState = floorstates.get(elevatorPosition);
        Floorstate targetFloorState = floorstates.get(targetFloor);
        return elevatorFloorState.getPossibleMoves().stream().parallel()
                .map(machines -> this.toBuilder()
                        .withFloor(elevatorFloorState.withoutMachines(machines))
                        .withFloor(targetFloorState.withMachines(machines))
                        .elevatorAtFloor(targetFloor)
                        .build())
                .collect(Collectors.toList());
    }

    public boolean isValid(MachineDefinition[] machineDefinitions) {
        return floorstates.values().stream()
                .allMatch(fs -> fs.isValid(machineDefinitions));
    }

    public boolean isComplete(Machines allMachines) {
        return elevatorPosition == Floor.FOURTH && floorstates.get(Floor.FOURTH).hasMachines(allMachines);
    }

    public StateBuilder toBuilder() {
        return new StateBuilder(this);
    }


    public Floor getElevatorPosition() {
        return elevatorPosition;
    }

    public Map<Floor, Floorstate> getFloorstates() {
        return floorstates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return elevatorPosition == state.elevatorPosition &&
                Objects.equals(floorstates, state.floorstates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elevatorPosition, floorstates);
    }
}

class StateBuilder {
    private Floor elevatorPosition;
    private Map<Floor, Floorstate> floorstates;

    StateBuilder(State state) {
        elevatorPosition = state.getElevatorPosition();
        floorstates = new HashMap<>();
        floorstates.putAll(state.getFloorstates());
    }

    public StateBuilder withFloor(Floorstate floorstate) {
        floorstates.put(floorstate.getFloor(), floorstate);
        return this;
    }

    public StateBuilder elevatorAtFloor(Floor floor) {
        elevatorPosition = floor;
        return this;
    }

    public State build() {
        return new State(elevatorPosition, floorstates);
    }
}