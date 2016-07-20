package com.geomotiv.rubicon.utils;

/**
 * Created by Oleg on 7/19/16.
 */
public final class StringUtils {

    private static final String ONE = "1";

    private StringUtils() {
    }

    public static boolean isEmpty(String source) {
        return source == null || source.isEmpty();
    }

    static boolean getBooleanFromInt(String source) {
        return ONE.equals(source);
    }

    static Integer getIntegerFromString(String source) {
        Assert.notNull(source);
        return Integer.valueOf(source);
    }

    static Float getFloatFromString(String source) {
        Assert.notNull(source);
        return Float.valueOf(source);
    }
}
