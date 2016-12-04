package com.adventofcode.year2016.day2;

public interface Keypad {
    void pressKeys(Instructions instructions);

    String getCode();


    default int clamp(int value, int min, int max) {
        return Math.min(max, Math.max(min, value));
    }

}
