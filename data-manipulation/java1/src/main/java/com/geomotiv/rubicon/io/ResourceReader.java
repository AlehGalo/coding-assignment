package com.geomotiv.rubicon.io;

/**
 * Created by Oleg on 7/18/16.
 */
public interface ResourceReader<T, E> {

    T readResource(E e);

}