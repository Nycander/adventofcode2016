package com.adventofcode.year2016.day5;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
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

    public String getPasswordForDoorOfLength(String doorId, int size) {
        return LongStream.rangeClosed(0, Long.MAX_VALUE)
                .parallel()
                .mapToObj(l -> doorId + l)
                .map(PasswordCracker::md5)
                .filter(b -> b[0] == 0)
                .filter(b -> b[1] == 0)
                .filter(b -> (b[2] & 0xf0) == 0)
                .map(bytes -> String.valueOf(bytesToString(bytes).charAt(5)))
                .sequential()
                .limit(size)
                .collect(Collectors.joining());
    }

    public String getAdvancedPasswordForDoorOfLength(String doorId, int size) {
        return LongStream.rangeClosed(0, Long.MAX_VALUE)
                .parallel()
                .mapToObj(l -> doorId + l)
                .map(PasswordCracker::md5)
                .filter(b -> b[0] == 0)
                .filter(b -> b[1] == 0)
                .filter(b -> (b[2] & 0xf0) == 0)
                .map(this::getCharacterAndPosition)
                .filter(Objects::nonNull)
                .sequential()
                .distinct()
                .limit(size)
                .reduce(new Password(size), Password::withCharacter, Password::merge)
                .asString();
    }


    private CharacterAtPosition getCharacterAndPosition(byte[] bytes) {
        // the sixth character represents the position (0-7),
        // and the seventh character is the character to put in that position.
        String string = bytesToString(bytes);
        char position = string.charAt(5);
        if (position < '0' || position > '7') {
            return null; // Invalid position
        }
        char character = string.charAt(6);
        return new CharacterAtPosition(Byte.parseByte(String.valueOf(position)), character);
    }

    private static byte[] md5(String message) {
        return md5.digest(message.getBytes());
    }

    private static String bytesToString(byte[] message) {
        return String.format("%032x", new BigInteger(1, message));
    }
}