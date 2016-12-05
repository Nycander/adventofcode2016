package com.adventofcode.year2016.day5;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class PasswordCracker {
    private final static MessageDigest md5;

    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }


    public String getPasswordForDoor(String doorId) {
        return LongStream.rangeClosed(0, Long.MAX_VALUE)
                .mapToObj(l -> doorId + l)
                .map(PasswordCracker::md5)
                .filter(b -> b[0] == 0)
                .filter(b -> b[1] == 0)
                .filter(b -> (b[2] & 0xf0) == 0)
                .map(bytes -> String.valueOf(bytesToString(bytes).charAt(5)))
                .limit(8)
                .collect(Collectors.joining());
    }

    private static byte[] md5(String message) {
        return md5.digest(message.getBytes());
    }

    private static String bytesToString(byte[] message) {
        return String.format("%032x", new BigInteger(1, message));
    }
}
