package com.adventofcode.year2016.day4;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class RoomTest {
    @Test
    public void parse() throws Exception {
        Room room = Room.parse("aaaaa-bbb-z-y-x-123[abxyz]");

        assertThat(room.getName(), equalTo("aaaaa-bbb-z-y-x"));
        assertThat(room.getSectorId(), equalTo("123"));
        assertThat(room.getChecksum(), equalTo("abxyz"));
    }

    @Test
    public void parse2() throws Exception {
        Room room = Room.parse("a-b-c-d-e-f-g-h-987[abcde]");

        assertThat(room.getName(), equalTo("a-b-c-d-e-f-g-h"));
        assertThat(room.getSectorId(), equalTo("987"));
        assertThat(room.getChecksum(), equalTo("abcde"));
    }

    @Test
    public void parse3() throws Exception {
        Room room = Room.parse("totally-real-room-200[decoy]");

        assertThat(room.getName(), equalTo("totally-real-room"));
        assertThat(room.getSectorId(), equalTo("200"));
        assertThat(room.getChecksum(), equalTo("decoy"));
    }

    /*
    To decrypt a room name, rotate each letter forward through the alphabet a number of times equal to the room's sector ID.
    A becomes B, B becomes C, Z becomes A, and so on. Dashes become spaces.

    For example, the real name for qzmt-zixmtkozy-ivhz-343 is very encrypted name.

    What is the sector ID of the room where North Pole objects are stored?
     */
    @Test
    public void decryptedName() throws Exception {
        assertThat(Room.parse("qzmt-zixmtkozy-ivhz-343[mkzys]").getDecryptedName(), equalTo("very encrypted name"));
    }
}