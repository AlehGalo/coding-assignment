package com.geomotiv.rubicon.utils;

import org.junit.Test;

/**
 * Created by Oleg on 7/25/16.
 */
public class AssertTest {

    @Test
    public void notEmpty() {
        Assert.notEmpty("a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullValue() {
        Assert.notEmpty(null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void emptyValue() {
        Assert.notEmpty("");
    }
}
