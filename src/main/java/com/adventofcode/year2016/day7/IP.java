package com.adventofcode.year2016.day7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class IP {
    private final List<String> normalParts;
    private final List<String> hypernets;

    public IP(List<String> normalParts, List<String> hypernets) {
        this.normalParts = Collections.unmodifiableList(normalParts);
        this.hypernets = Collections.unmodifiableList(hypernets);
    }

    public static IP parse(String potentialIp) {
        List<String> normalParts = new ArrayList<>();
        List<String> hypernets = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : potentialIp.toCharArray()) {
            switch (c) {
                case '[':
                    normalParts.add(sb.toString());
                    sb = new StringBuilder();
                    break;
                case ']':
                    hypernets.add(sb.toString());
                    sb = new StringBuilder();
                    break;
                default:
                    sb.append(c);
            }
        }
        normalParts.add(sb.toString());
        return new IP(normalParts, hypernets);
    }

    public boolean supportsTLS() {
        return normalParts.stream().anyMatch(IP::hasAutonomousBridgeBypassAnnotation) && hypernets.stream().noneMatch(IP::hasAutonomousBridgeBypassAnnotation);
    }

    // Autonomous Bridge Bypass Annotation
    private static boolean hasAutonomousBridgeBypassAnnotation(String addressPart) {
        char[] chars = addressPart.toCharArray();
        for (int i = 2; i < chars.length - 1; i++) {
            if (chars[i] == chars[i - 1]
                    && chars[i - 2] == chars[i + 1]
                    && chars[i] != chars[i + 1]) {
                return true;
            }
        }
        return false;
    }


    // An IP supports SSL if it has an Area-Broadcast Accessor, or ABA, anywhere in the supernet sequences
    // (outside any square bracketed sections), and a corresponding Byte Allocation Block, or BAB,
    // anywhere in the hypernet sequences.
    public boolean supportsSSL() {
        return normalParts.stream()
                .flatMap(IP::getAreaBroadcastAccessors)
                .anyMatch(aba -> hypernets.stream().anyMatch(net -> hasByteAllocationBlock(net, aba)));
    }

    // A corresponding BAB is the same characters but in reversed positions: yxy and bab, respectively.
    private static boolean hasByteAllocationBlock(String part, String aba) {
        return part.contains(new String(new char[]{aba.charAt(1), aba.charAt(0), aba.charAt(1)}));
    }

    // An ABA is any three-character sequence which consists of the same character twice with a different character between them
    private static Stream<String> getAreaBroadcastAccessors(String part) {
        List<String> abas = new ArrayList<>();
        char[] chars = part.toCharArray();
        for (int i = 1; i < chars.length - 1; i++) {
            if (chars[i] != chars[i - 1] && chars[i - 1] == chars[i + 1]) {
                abas.add(part.substring(i - 1, i + 2));
            }
        }
        return abas.stream();
    }

    public List<String> getNormalParts() {
        return normalParts;
    }

    public List<String> getHypernets() {
        return hypernets;
    }
}
