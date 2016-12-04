package com.adventofcode.year2016.day2;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class TraditionalKeypadTest {
    @Test
    public void untouchedKeypadHasCode5() throws Exception {
        Assert.assertThat(new TraditionalKeypad().getCode(), equalTo("5"));
    }

    @Test
    public void keypad_D() throws Exception {
        Keypad keypad = new TraditionalKeypad();
        keypad.pressKeys(Instructions.parse("D"));
        Assert.assertThat(keypad.getCode(), equalTo("8"));
    }

    @Test
    public void keypad_DD_clamps() throws Exception {
        Keypad keypad = new TraditionalKeypad();
        keypad.pressKeys(Instructions.parse("DD"));
        Assert.assertThat(keypad.getCode(), equalTo("8"));
    }

    @Test
    public void keypad_RR_clamps() throws Exception {
        Keypad keypad = new TraditionalKeypad();
        keypad.pressKeys(Instructions.parse("R"));
        Assume.assumeThat(keypad.getCode(), equalTo("6"));
        keypad.pressKeys(Instructions.parse("R"));
        Assert.assertThat(keypad.getCode(), equalTo("6"));
    }

    @Test
    public void example() throws Exception {
        Keypad keypad = new TraditionalKeypad();
        keypad.pressKeys(Instructions.parse("ULL"));
        Assert.assertThat(keypad.getCode(), equalTo("1"));
        keypad.pressKeys(Instructions.parse("RRDDD"));
        Assert.assertThat(keypad.getCode(), equalTo("9"));
        keypad.pressKeys(Instructions.parse("LURDL"));
        Assert.assertThat(keypad.getCode(), equalTo("8"));
        keypad.pressKeys(Instructions.parse("UUUUD"));
        Assert.assertThat(keypad.getCode(), equalTo("5"));
    }
}