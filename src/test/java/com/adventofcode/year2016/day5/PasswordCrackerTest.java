package com.adventofcode.year2016.day5;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class PasswordCrackerTest {
    private final PasswordCracker passwordCracker = new PasswordCracker();

    @Test
    public void normalPassword() throws Exception {
        assertThat(passwordCracker.getPasswordForDoorOfLength("cxdnnyjw", 8), equalTo("f77a0e6e"));
    }

    @Test
    public void advancedPassword() throws Exception {
        assertThat(passwordCracker.getAdvancedPasswordForDoorOfLength("cxdnnyjw", 8), equalTo("999828ec"));
    }

}