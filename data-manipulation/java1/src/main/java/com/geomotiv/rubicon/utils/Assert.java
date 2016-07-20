package com.geomotiv.rubicon.utils;

/**
 * Created by Oleg on 7/18/16.
 */
public final class Assert {

    private Assert() {
    }

    public static void notNull(Object obj) {
        if (obj == null) throw new IllegalArgumentException();
    }

    public static boolean notEmptyOrNull(final String string) {
        return string == null || string.isEmpty();
    }
}
