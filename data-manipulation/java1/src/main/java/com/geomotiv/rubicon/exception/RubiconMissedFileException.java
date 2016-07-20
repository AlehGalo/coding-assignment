package com.geomotiv.rubicon.exception;

/**
 * Created by Oleg on 7/20/16.
 */
public class RubiconMissedFileException extends RubiconException {

    public RubiconMissedFileException(String message) {
        super(message);
    }

    public RubiconMissedFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public RubiconMissedFileException(Throwable cause) {
        super(cause);
    }

    public RubiconMissedFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
