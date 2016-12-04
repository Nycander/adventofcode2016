package com.adventofcode.year2016.day2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

/*
 --- Part Two ---

You finally arrive at the bathroom (it's a several minute walk from the lobby so visitors can behold the many fancy
conference rooms and water coolers on this floor) and go to punch in the code. Much to your bladder's dismay, the
keypad is not at all like you imagined it. Instead, you are confronted with the result of hundreds of man-hours
of bathroom-keypad-design meetings:

    1
  2 3 4
5 6 7 8 9
  A B C
    D
You still start at "5" and stop when you're at an edge, but given the same instructions as above, the outcome is very different:

You start at "5" and don't move at all (up and left are both edges), ending at 5.
Continuing from "5", you move right twice and down three times (through "6", "7", "B", "D", "D"), ending at D.
Then, from "D", you move five more times (through "D", "B", "C", "C", "B"), ending at B.
Finally, after five more moves, you end at 3.
So, given the actual keypad layout, the code would be 5DB3.

Using the same instructions in your puzzle input, what is the correct bathroom code?
 */
public class StarShapedKeypadTest {
    private StarShapedKeypad keypad;

    @Before
    public void setup() {
        keypad = new StarShapedKeypad();
    }

    @Test
    public void d() throws Exception {
        Assert.assertThat(keypad.getCode(), equalTo("5"));
        keypad.pressKeys(Instructions.parse("D"));
        Assert.assertThat(keypad.getCode(), equalTo("5"));
    }


    @Test
    public void rd() throws Exception {
        keypad.pressKeys(Instructions.parse("R"));
        Assert.assertThat(keypad.getCode(), equalTo("6"));
        keypad.pressKeys(Instructions.parse("D"));
        Assert.assertThat(keypad.getCode(), equalTo("A"));
    }

    @Test
    public void rrrr() throws Exception {
        keypad.pressKeys(Instructions.parse("RRRR"));
        Assert.assertThat(keypad.getCode(), equalTo("9"));
    }

}