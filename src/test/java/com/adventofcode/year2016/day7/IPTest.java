package com.adventofcode.year2016.day7;

import org.junit.Test;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class IPTest {

    @Test
    public void parse() throws Exception {
        assertThat(IP.parse("abba[mnop]qrst").getHypernets(), hasItems("mnop"));
        assertThat(IP.parse("abba[mnop]qrst").getNormalParts(), hasItems("abba", "qrst"));
    }

    @Test
    public void parseLongText() throws Exception {
        assertThat(IP.parse("ektijwczwnlancuqfv[luqhtfgwmlilhwnk]gxgivxlnerdhbhetfz[bzczfdorrsptzikjmct]mfrsvxgxijtusmvjd[sbpnwycbrykuhsinudc]bmpikuskzlxcoidp").getNormalParts(),
                hasItems("ektijwczwnlancuqfv", "gxgivxlnerdhbhetfz", "mfrsvxgxijtusmvjd", "bmpikuskzlxcoidp"));
        assertThat(IP.parse("ektijwczwnlancuqfv[luqhtfgwmlilhwnk]gxgivxlnerdhbhetfz[bzczfdorrsptzikjmct]mfrsvxgxijtusmvjd[sbpnwycbrykuhsinudc]bmpikuskzlxcoidp").getHypernets(),
                hasItems("luqhtfgwmlilhwnk", "bzczfdorrsptzikjmct", "sbpnwycbrykuhsinudc"));
    }

    @Test
    public void supportsTLS() throws Exception {
        assertTrue(IP.parse("abba[mnop]qrst").supportsTLS());
    }

    @Test
    public void unsupportedTLS_abbaWithinHypernet() throws Exception {
        assertFalse(IP.parse("abcd[bddb]xyyx").supportsTLS());
    }

    @Test
    public void unsupportedTLS_same_interior_chars() throws Exception {
        assertFalse(IP.parse("aaaa[qwer]tyui").supportsTLS());
    }

    @Test
    public void supportsTLS_with_6_chars() throws Exception {
        assertTrue(IP.parse("ioxxoj[asdfgh]zxcvbn").supportsTLS());
    }

    @Test
    public void supportsSSL() throws Exception {
        assertTrue(IP.parse("aba[bab]xyz").supportsSSL());
    }

    @Test
    public void unsupportedSSL_missingBab() throws Exception {
        assertFalse(IP.parse("xyx[xyx]xyx").supportsSSL());
    }

    @Test
    public void supportsSSL2() throws Exception {
        assertTrue(IP.parse("aaa[kek]eke").supportsSSL());
    }

    @Test
    public void supportsSSL3() throws Exception {
        assertTrue(IP.parse("zazbz[bzb]cdb").supportsSSL());

    }
}