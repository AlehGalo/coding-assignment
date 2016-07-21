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

    public static void notEmpty(String string) {
        if (StringUtils.isEmptyOrNull(string)) {
            throw new IllegalArgumentException();
        }
    }
}
