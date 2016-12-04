package com.adventofcode.year2016.day2;

import java.util.Scanner;

public class Day2 {
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);

        String traditionalCode = "", starShapedCode = "";
        Keypad traditionalKeypad = new TraditionalKeypad();
        Keypad starShapedKeypad = new StarShapedKeypad();

        for (int i = 0; scanner.hasNext(); i++) {
            String line = scanner.nextLine();

            Instructions instructions = Instructions.parse(line);

            traditionalKeypad.pressKeys(instructions);
            starShapedKeypad.pressKeys(instructions);

            traditionalCode += traditionalKeypad.getCode();
            starShapedCode += starShapedKeypad.getCode();

            System.out.println("1st star: " + traditionalCode);
            System.out.println("2nd star: " + starShapedCode);
        }
    }

}
