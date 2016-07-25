package com.geomotiv.rubicon.exception;

/**
 * <p>Exception faces the fact that reader for any appropriate format is missed.</p>
 * <p>
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
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
