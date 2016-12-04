package com.adventofcode.year2016.day3;

import org.junit.Assert;
import org.junit.Test;

public class TriangleTest {
    @Test
    public void invalidExample() throws Exception {
        Assert.assertFalse(Triangle.parse("5 10 25").isValid());
        Assert.assertFalse(Triangle.parse("25 10 5").isValid());
        Assert.assertFalse(Triangle.parse("10 25 5").isValid());
    }

    @Test
    public void valid() throws Exception {
        Assert.assertTrue(Triangle.parse("5 10 10").isValid());
    }
}