package com.adventofcode.year2016.day9;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ExperimentalFileV2Test {

    @Test
    public void noCompression() throws Exception {
        assertThat(ExperimentalFileV2.decompress("ADVENT").getLength(), equalTo(6));
    }

    @Test
    public void simpleCompression() throws Exception {
        assertThat(ExperimentalFileV2.decompress("A(1x5)BC").getLength(), equalTo(7));
    }

    @Test
    public void multipleCharacterCompression() throws Exception {
        assertThat(ExperimentalFileV2.decompress("(3x3)XYZ").getLength(), equalTo(9));
    }

    @Test
    public void compression3() throws Exception {
        assertThat(ExperimentalFileV2.decompress("A(2x2)BCD(2x2)EFG").getLength(), equalTo(11));
    }


    @Test
    public void nestedCompression() throws Exception {
        assertThat(ExperimentalFileV2.decompress("X(8x2)(3x3)ABCY").getLength(), equalTo(20));
    }

    @Test
    public void lotsOfA() throws Exception {
        assertThat(ExperimentalFileV2.decompress("(27x12)(20x12)(13x14)(7x10)(1x12)A").getLength(), equalTo(241920));
    }

    @Test
    public void complicated() throws Exception {
        assertThat(ExperimentalFileV2.decompress("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN").getLength(), equalTo(445));
    }
}