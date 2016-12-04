package com.adventofcode.year2016.day4;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RoomVerifier {
    // A room is real (not a decoy) if the checksum is the five most common letters in the encrypted name, in order, with ties broken by alphabetization.
    public boolean isDecoy(Room room) {

        Map<String, Long> countedLetters = Stream.of(room.getName().split(""))
                .filter(s -> !s.equals("-"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        String actualChecksum = countedLetters.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(5)
                .map(Map.Entry::getKey)
                .map(s -> s.substring(0, 1))
                .collect(Collectors.joining());

        return !actualChecksum.equals(room.getChecksum());
    }
}
