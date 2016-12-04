package com.adventofcode.year2016.day3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Triangle {
    private final List<Integer> lengths;

    public Triangle(List<Integer> lengths) {
        if (lengths.size() != 3) {
            throw new IllegalArgumentException("Triangle must have 3 lengths");
        }
        this.lengths = Collections.unmodifiableList(new ArrayList<>(lengths));
    }


    public static Triangle parse(String line) {
        return new Triangle(Stream.of(line.trim().split("\\s+"))
                .map(Integer::valueOf)
                .collect(Collectors.toList()));
    }

    // the sum of any two sides must be larger than the remaining side
    public boolean isValid() {
        if (lengths.get(0) + lengths.get(1) <= lengths.get(2)) return false;
        if (lengths.get(1) + lengths.get(2) <= lengths.get(0)) return false;
        if (lengths.get(2) + lengths.get(0) <= lengths.get(1)) return false;
        return true;
    }
}
