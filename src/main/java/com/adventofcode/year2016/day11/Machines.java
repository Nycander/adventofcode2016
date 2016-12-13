package com.adventofcode.year2016.day11;

import java.util.HashSet;
import java.util.Set;

class Machines {
    private final int machines;

    Machines(int machines) {
        this.machines = machines;
    }

    public static Machines empty() {
        return Machines.valueOf(0);
    }

    public static Machines valueOf(int machines) {
        return new Machines(machines);
    }

    public boolean isEmpty() {
        return machines == 0;
    }

    public Set<Machines> getUnigrams() {
        Set<Machines> monograms = new HashSet<>();
        for (int i = 1; i <= machines; i <<= 1) {
            int machine = machines & i;
            if (machine != 0) {
                monograms.add(Machines.valueOf(machine));
            }
        }
        return monograms;
    }

    public Set<Machines> getBigrams() {
        Set<Machines> bigrams = new HashSet<>();
        Set<Machines> unigrams = getUnigrams();
        for (Machines first : unigrams) {
            for (Machines second : unigrams) {
                if (first.equals(second)) continue;
                bigrams.add(first.with(second));
            }
        }
        return bigrams;
    }

    public boolean has(Machines search) {
        return (machines & search.machines) == search.machines;
    }

    public Machines without(Machines machinesToRemove) {
        return Machines.valueOf(machines & ~machinesToRemove.machines);
    }

    public Machines with(Machines machinesToAdd) {
        return Machines.valueOf(machines | machinesToAdd.machines);
    }

    @Override
    public int hashCode() {
        return machines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Machines other = (Machines) o;
        return machines == other.machines;
    }

    public static Machines from(MachineDefinition[] allMachines) {
        int m = 0;
        for (MachineDefinition allMachine : allMachines) {
            m |= allMachine.getId();
        }
        return Machines.valueOf(m);
    }
}
