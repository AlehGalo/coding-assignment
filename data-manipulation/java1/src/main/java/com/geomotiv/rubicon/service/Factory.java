package com.geomotiv.rubicon.service;

import com.geomotiv.rubicon.exception.RubiconException;

/**
 * <p>Factory interface for creating the object.</p>
 * <p>
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
public interface Factory<T> {

    T createObject() throws RubiconException;
}
