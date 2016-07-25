package com.geomotiv.rubicon.utils;

/**
 * <p>.</p>
 * <p>
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
public final class Assert {

    private Assert() {
    }

    public static void notEmpty(String string) {
        if (StringUtils.isEmptyOrNull(string)) {
            throw new IllegalArgumentException();
        }
    }
}
