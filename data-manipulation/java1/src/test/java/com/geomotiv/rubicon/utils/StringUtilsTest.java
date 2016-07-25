package com.geomotiv.rubicon.utils;

import org.junit.*;

/**
 * Created by Oleg on 7/25/16.
 */
public class StringUtilsTest {

    @Test
    public void nullObject(){
        org.junit.Assert.assertTrue(StringUtils.isEmptyOrNull(null));
    }

    @Test
    public void emptyObject(){
        org.junit.Assert.assertTrue(StringUtils.isEmptyOrNull(""));
    }

    @Test
    public void nonEmpty(){
        org.junit.Assert.assertFalse(StringUtils.isEmptyOrNull("any String"));
    }

    @Test
    public void spacesEmpty(){
        org.junit.Assert.assertFalse(StringUtils.isEmptyOrNull(" "));
    }
}
