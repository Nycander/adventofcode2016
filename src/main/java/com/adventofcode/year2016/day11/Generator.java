package com.adventofcode.year2016.day11;

import java.util.HashMap;
import java.util.Map;

final class Generator extends Machine {
    private static final Map<String, Generator> flyweights = new HashMap<>();

    private Generator(String type) {
        super(type);
    }

    public static Generator valueOf(String type) {
        if (flyweights.containsKey(type)) {
            return flyweights.get(type);
        }
        Generator generator = new Generator(type);
        flyweights.put(type, generator);
        return generator;
    }

    @Override
    public String toString() {
        return getType() + " generator";
    }
}
