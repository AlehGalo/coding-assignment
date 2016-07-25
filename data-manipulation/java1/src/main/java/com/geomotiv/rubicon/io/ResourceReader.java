package com.geomotiv.rubicon.io;

import com.geomotiv.rubicon.exception.RubiconException;

/**
 * <p>Interface for making any resource read.</p>
 *
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
public interface ResourceReader<T, E> {

    T readResource(E e) throws RubiconException;

}