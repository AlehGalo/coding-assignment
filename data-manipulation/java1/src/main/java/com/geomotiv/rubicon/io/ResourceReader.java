package com.geomotiv.rubicon.io;

import com.geomotiv.rubicon.exception.RubiconException;

/**
 * Created by Oleg on 7/18/16.
 */
public interface ResourceReader<T, E> {

    T readResource(E e) throws RubiconException;

}