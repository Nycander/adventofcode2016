package com.adventofcode.year2016.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
--- Part Two ---

Now that you've helpfully marked up their design documents, it occurs to you that triangles are specified in groups of three vertically. Each set of three numbers in a column specifies a triangle. Rows are unrelated.

For example, given the following specification, numbers with the same hundreds digit would be part of the same triangle:

101 301 501
102 302 502
103 303 503
201 401 601
202 402 602
203 403 603
In your puzzle input, and instead reading by columns, how many of the listed triangles are possible?

 */
public class Day3_Part2 {
    public static void main(String... args) {
        int validTriangles = 0;
        Scanner scanner = new Scanner(System.in);
        List<String[]> entries = new ArrayList<>();

        // transpose each 3x3 matrix manually
        while (scanner.hasNext()) {
            String[] line1 = scanner.nextLine().trim().split("\\s+");
            if (line1.length < 3) {
                break;
            }
            String[] line2 = scanner.nextLine().trim().split("\\s+");
            String[] line3 = scanner.nextLine().trim().split("\\s+");

            for (int i = 0; i < 3; i++) {
                String potential = line1[i] + " " + line2[i] + " " + line3[i];
                System.out.println(potential);
                validTriangles += Triangle.parse(potential).isValid() ? 1 : 0;
            }
        }
        System.out.println(validTriangles);
    }
}
