package com.adventofcode.year2016.day11;

import java.util.HashMap;
import java.util.Map;

final class Microchip extends Machine {
    private static final Map<String, Microchip> flyweights = new HashMap<>();

    private Microchip(String type) {
        super(type);
    }

    public static Microchip valueOf(String type) {
        if (flyweights.containsKey(type)) {
            return flyweights.get(type);
        }
        Microchip microchip = new Microchip(type);
        flyweights.put(type, microchip);
        return microchip;
    }

    @Override
    public String toString() {
        return getType() + "-compatible microchip";
    }
}
