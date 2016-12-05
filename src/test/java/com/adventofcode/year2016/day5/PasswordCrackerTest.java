package com.adventofcode.year2016.day5;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class PasswordCrackerTest {

    @Test
    public void aVoid() throws Exception {
        Assert.assertThat(new PasswordCracker().getPasswordForDoor("cxdnnyjw"), CoreMatchers.equalTo("f77a0e6e"));
    }

    @Test
    public void byteArrays() throws Exception {
        byte[] bytes1 = {0, 0, 15, -31, -23, 32, -128, -71, -107, 27, 5, 62, 112, -29, 31, -53};
        byte[] bytes2 = {0, 0, 7, -56, 39, 18, 108, -127, -6, 102, 66, 17, 105, 63, 37, 64};
        byte[] bytes3 = {0, 0, 10, 110, 34, 82, 83, -74, -86, -88, -14, 14, -5, -86, -40, -75};
    }

}