package com.adventofcode.year2016.day2;

public class StarShapedKeypad implements Keypad {
    private String[][] pad = new String[][]{
            new String[]{" ", " ", "1", " ", " "},
            new String[]{" ", "2", "3", "4", " "},
            new String[]{"5", "6", "7", "8", "9"},
            new String[]{" ", "A", "B", "C", " "},
            new String[]{" ", " ", "D", " ", " "},
    };

    private int x = 0, y = 2;

    @Override
    public void pressKeys(Instructions instructions) {
        for (KeypadMovement movement : instructions.getKeypadMovements()) {
            x += movement.dx;
            y += movement.dy;

            x = clamp(x, 0, pad[0].length - 1);
            y = clamp(y, 0, pad.length - 1);

            if (getCode().equals(" ")) {
                x -= movement.dx;
                y -= movement.dy;
            }
        }
    }

    @Override
    public String getCode() {
        return pad[y][x];
    }
}
