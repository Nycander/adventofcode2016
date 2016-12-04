package com.adventofcode.year2016.day4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

// Each room consists of an encrypted name (lowercase letters separated by dashes) followed by a dash, a sector ID, and a checksum in square brackets.
public class Room {
    private static final String nameRegex = "(?<name>(-?\\w+)+)";
    private static final String sectorIdRegex = "(?<sectorId>\\d+)";
    private static final String checksumRegex = "(?<checksum>\\w+)";
    private static final Pattern roomRegex = Pattern.compile("^" + nameRegex + "-" + sectorIdRegex + "\\[" + checksumRegex + "\\]$");

    private final String name;
    private final int sectorId;
    private final String checksum;

    public Room(String name, String sectorId, String checksum) {
        if (!name.matches(nameRegex)) throw new IllegalArgumentException("name");
        if (!sectorId.matches(sectorIdRegex)) throw new IllegalArgumentException("sectorId");
        if (!checksum.matches(checksumRegex)) throw new IllegalArgumentException("checksum");

        this.name = name;
        this.sectorId = Integer.parseInt(sectorId);
        this.checksum = checksum;
    }

    public static Room parse(String line) {
        Matcher matcher = roomRegex.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(line);
        }
        String name = matcher.group("name");
        String sectorId = matcher.group("sectorId");
        String checksum = matcher.group("checksum");
        return new Room(name, sectorId, checksum);
    }

    public String getName() {
        return name;
    }

    /*
     To decrypt a room name, rotate each letter forward through the alphabet a number of times equal to the room's sector ID.
     A becomes B, B becomes C, Z becomes A, and so on. Dashes become spaces.
     */
    public String getDecryptedName() {
        return name.chars()
                .map(this::decryptCharacter)
                .mapToObj(value -> String.valueOf((char) value))
                .collect(Collectors.joining());
    }

    private int decryptCharacter(int character) {
        if (character == '-') return ' ';
        char start = 'a';
        int alphabetLength = 'z' - start + 1;
        return ((character - start + sectorId) % alphabetLength) + start;
    }


    public int getSectorId() {
        return sectorId;
    }

    public String getChecksum() {
        return checksum;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + "'" +
                ", decryptedName='" + getDecryptedName() + "'" +
                ", sectorId=" + sectorId +
                ", checksum='" + checksum + '\'' +
                '}';
    }
}
