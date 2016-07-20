package com.geomotiv.rubicon.service;

/**
 * Created by Oleg on 7/20/16.
 */
public interface Extractable<T, E> {

    T extract(Iterable<E> iterable);

}
