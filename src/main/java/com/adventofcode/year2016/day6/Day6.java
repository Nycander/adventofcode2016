package com.adventofcode.year2016.day6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
--- Day 6: Signals and Noise ---

Something is jamming your communications with Santa. Fortunately, your signal is only partially jammed, and protocol
in situations like this is to switch to a simple repetition code to get the message through.

In this model, the same message is sent repeatedly. You've recorded the repeating message signal (your puzzle input),
but the data seems quite corrupted - almost too badly to recover. Almost.

All you need to do is figure out which character is most frequent for each position.
For example, suppose you had recorded the following messages:

eedadn
drvtee
eandsr
raavrd
atevrs
tsrnev
sdttsa
rasrtv
nssdts
ntnada
svetve
tesnvt
vntsnd
vrdear
dvrsen
enarar
The most common character in the first column is e; in the second, a; in the third, s, and so on.
Combining these characters returns the error-corrected message, easter.

Given the recording in your puzzle input, what is the error-corrected version of the message being sent?

--- Part Two ---

Of course, that would be the message - if you hadn't agreed to use a modified repetition code instead.

In this modified code, the sender instead transmits what looks like random data, but for each character, the character they actually want to send is slightly less likely than the others. Even after signal-jamming noise, you can look at the letter distributions in each column and choose the least common letter to reconstruct the original message.

In the above example, the least common character in the first column is a; in the second, d, and so on. Repeating this process for the remaining characters produces the original message, advent.

Given the recording in your puzzle input and this new decoding methodology, what is the original message that Santa is trying to send?

Although it hasn't changed, you can still get your puzzle input.
 */
public class Day6 {
    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);

        List<List<String>> lines = new ArrayList<>();

        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                break;
            }
            lines.add(Stream.of(line.split("")).collect(Collectors.toList()));
        }

        System.out.println(part1(lines));
        System.out.println(part2(lines));
    }

    private static String part1(List<List<String>> lines) {
        String message = "";
        int messageLength = lines.get(0).size();
        for (int i = 0; i < messageLength; i++) {
            final int finalI = i;
            message += lines.stream()
                    .map(line -> line.get(finalI))
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .get();
        }
        return message;
    }


    private static String part2(List<List<String>> lines) {
        String message = "";
        int messageLength = lines.get(0).size();
        for (int i = 0; i < messageLength; i++) {
            final int finalI = i;
            message += lines.stream()
                    .map(line -> line.get(finalI))
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .get();
        }
        return message;
    }
}
