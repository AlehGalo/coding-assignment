package com.geomotiv.rubicon.exception;

/**
 * <p>Top level application exception.</p>
 *
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
public class RubiconException extends Exception {

    public RubiconException(String message) {
        super(message);
    }

    public RubiconException(String message, Throwable cause) {
        super(message, cause);
    }

    public RubiconException(Throwable cause) {
        super(cause);
    }

    public RubiconException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
