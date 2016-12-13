package com.adventofcode.year2016.day11;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FloorStateParser {
    public interface FloorStateVisitor {
        void generator(Generator generator);
        void microchip(Microchip microchip);
        void floorState(Floor floor, Set<Machine> machines);
    }

    static Pattern nothingRelevant = Pattern.compile("^The (?<floor>\\w+) floor contains nothing relevant.$");

    static Pattern contains = Pattern.compile("^The (?<floor>\\w+) floor contains (?<contains>.+).$");
    static Pattern generatorPattern = Pattern.compile("a (?<type>\\w+) generator");

    static Pattern chipPattern = Pattern.compile("a (?<type>\\w+)-compatible microchip");
    private final FloorStateVisitor visitor;


    public FloorStateParser(FloorStateVisitor visitor) {
        this.visitor = Objects.requireNonNull(visitor);
    }

    public void parseLine(String line) {
        Matcher nothingMatcher = nothingRelevant.matcher(line);
        if (nothingMatcher.matches()) {
            Floor floor = parseFloor(nothingMatcher);
            visitor.floorState(floor, Collections.emptySet());
            return;
        }

        Matcher containsMatcher = contains.matcher(line);
        if (containsMatcher.matches()) {
            Floor floor = parseFloor(containsMatcher);
            String stuff = containsMatcher.group("contains");

            Set<Machine> machines = new HashSet<>();
            Matcher genMatcher = generatorPattern.matcher(stuff);
            while (genMatcher.find()) {
                Generator generator = Generator.valueOf(genMatcher.group("type"));
                machines.add(generator);
                visitor.generator(generator);
            }

            Matcher chipMatcher = chipPattern.matcher(stuff);
            while (chipMatcher.find()) {
                Microchip microchip = Microchip.valueOf(chipMatcher.group("type"));
                machines.add(microchip);
                visitor.microchip(microchip);
            }

            visitor.floorState(floor, machines);
            return;
        }

        throw new IllegalArgumentException(line);
    }

    private static Floor parseFloor(Matcher matcher) {
        return Floor.valueOf(matcher.group("floor").toUpperCase());
    }
}
