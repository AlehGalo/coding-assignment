package com.geomotiv.rubicon.exception;

/**
 * Created by Oleg on 7/20/16.
 */
public class RubiconIOException extends RubiconException {

    public RubiconIOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public RubiconIOException(String message) {
        super(message);
    }

    public RubiconIOException(String message, Throwable cause) {
        super(message, cause);
    }

    public RubiconIOException(Throwable cause) {
        super(cause);
    }
}
