package com.geomotiv.rubicon.exception;

/**
 * Created by Oleg on 7/20/16.
 */
public class RubiconMissedReaderException extends RubiconException {

    public RubiconMissedReaderException(String message) {
        super(message);
    }

    public RubiconMissedReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public RubiconMissedReaderException(Throwable cause) {
        super(cause);
    }

    public RubiconMissedReaderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
