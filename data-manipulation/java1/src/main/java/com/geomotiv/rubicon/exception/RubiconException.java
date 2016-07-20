package com.geomotiv.rubicon.exception;

/**
 * Created by Oleg on 7/20/16.
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
