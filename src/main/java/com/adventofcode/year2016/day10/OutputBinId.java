package com.adventofcode.year2016.day10;

class OutputBinId {
    private final int id;

    OutputBinId(int id) {
        this.id = id;
    }

    public static OutputBinId valueOf(int id) {
        return new OutputBinId(id);
    }

    public static OutputBinId valueOf(String id) {
        return new OutputBinId(Integer.parseInt(id));
    }
}