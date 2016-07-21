package com.geomotiv.rubicon.io;

import com.geomotiv.rubicon.exception.RubiconIOException;

/**
 * Created by Oleg on 7/21/16.
 */
public interface ResourceWriter<T> {

    void write(T t) throws RubiconIOException;
}
