package com.geomotiv.rubicon.utils;

import java.util.Objects;

/**
 * <p>Utility class to be used with String objects.</p>
 *
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
public final class StringUtils {

    private static final String ONE = "1";

    public static final String EMPTY = "";

    public static final char NEW_LINE = '\n';

    private StringUtils() {
    }

    public static boolean isEmptyOrNull(final String string) {
        return string == null || string.isEmpty();
    }

    public static boolean getBooleanFromInt(String source) {
        return ONE.equals(source);
    }

    public static Integer getIntegerFromString(String source) {
        Objects.requireNonNull(source);
        return Integer.valueOf(source);
    }

    public static Float getFloatFromString(String source) {
        Objects.requireNonNull(source);
        return Float.valueOf(source);
    }
}
