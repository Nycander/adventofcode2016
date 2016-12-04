package com.adventofcode.year2016.day2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Instructions {
    private final List<KeypadMovement> keypadMovements;

    private Instructions(List<KeypadMovement> keypadMovements) {
        this.keypadMovements = Collections.unmodifiableList(new ArrayList<>(keypadMovements));
    }

    public static Instructions create(List<KeypadMovement> keypadMovements) {
        return new Instructions(keypadMovements);
    }

    public static Instructions parse(String potentialInstructions) {
        return Instructions.create(potentialInstructions.chars()
                .mapToObj(ch -> KeypadMovement.valueOf((char) ch))
                .collect(Collectors.toList()));
    }

    public List<KeypadMovement> getKeypadMovements() {
        return keypadMovements;
    }
}
