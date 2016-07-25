package com.geomotiv.rubicon.io;

import com.geomotiv.rubicon.exception.RubiconIOException;

/**
 * <p>.</p>
 *
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
public interface ResourceWriter<T> {

    void write(T t) throws RubiconIOException;
}
