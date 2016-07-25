package com.geomotiv.rubicon.exception;

/**
 * <p>Any IO exception made checked to handle the cases of IOException and rethrow them to the upper level.</p>
 *
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
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
