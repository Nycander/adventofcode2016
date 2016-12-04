package com.adventofcode.year2016.day4;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/*
not-a-real-room-404[oarel] is a real room.
totally-real-room-200[decoy] is not.
 */
public class RoomVerifierTest {
    @Test
    public void realRoom() throws Exception {
        assertFalse(new RoomVerifier().isDecoy(Room.parse("aaaaa-bbb-z-y-x-123[abxyz]")));
    }

    @Test
    public void realRoomTied() throws Exception {
        assertFalse(new RoomVerifier().isDecoy(Room.parse("a-b-c-d-e-f-g-h-987[abcde]")));
    }

    @Test
    public void realRoolWeird() throws Exception {
        assertFalse(new RoomVerifier().isDecoy(Room.parse("not-a-real-room-404[oarel]")));
    }

    @Test
    public void decoy() throws Exception {
        assertTrue(new RoomVerifier().isDecoy(Room.parse("totally-real-room-200[decoy]")));
    }
}