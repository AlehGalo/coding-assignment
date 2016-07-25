package com.geomotiv.rubicon.service;

/**
 * <p>Marking interface for extracting any value from iterable.</p>
 *
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
public interface Extractable<T, E> {

    T extract(Iterable<E> iterable);

}
