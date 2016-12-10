package com.adventofcode.year2016.day9;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ExperimentalFileTest {
    @Test
    public void noCompression() throws Exception {
        ExperimentalFile file = ExperimentalFile.decompress("ADVENT");

        assertThat(file.getContents(), equalTo("ADVENT"));
        assertThat(file.getLength(), equalTo(6));
    }

    @Test
    public void simpleCompression() throws Exception {
        ExperimentalFile file = ExperimentalFile.decompress("A(1x5)BC");

        assertThat(file.getContents(), equalTo("ABBBBBC"));
        assertThat(file.getLength(), equalTo(7));
    }

    @Test
    public void multipleCharacterCompression() throws Exception {
        ExperimentalFile file = ExperimentalFile.decompress("(3x3)XYZ");

        assertThat(file.getContents(), equalTo("XYZXYZXYZ"));
        assertThat(file.getLength(), equalTo(9));
    }

    @Test
    public void compression3() throws Exception {
        ExperimentalFile file = ExperimentalFile.decompress("A(2x2)BCD(2x2)EFG");

        assertThat(file.getContents(), equalTo("ABCBCDEFEFG"));
        assertThat(file.getLength(), equalTo(11));
    }

    @Test
    public void nestedCompression() throws Exception {
        assertThat(ExperimentalFile.decompress("(6x1)(1x3)A").getContents(), equalTo("(1x3)A"));
    }

    @Test
    public void nestedCompression2() throws Exception {
        ExperimentalFile file = ExperimentalFile.decompress("X(8x2)(3x3)ABCY");

        assertThat(file.getContents(), equalTo("X(3x3)ABC(3x3)ABCY"));
        assertThat(file.getLength(), equalTo(18));
    }
}