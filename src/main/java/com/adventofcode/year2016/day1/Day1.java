package com.adventofcode.year2016.day1;

import java.util.Scanner;

public class Day1 {
    public static void main(String... args) {
        System.out.println("Insert route:");
        Route parse = Route.parse(new Scanner(System.in).nextLine());
        System.out.println("Total length: " + parse.getTotalLength());
        System.out.println("Length until first revisit: " + parse.getLengthToFirstRevisit());
    }
}
