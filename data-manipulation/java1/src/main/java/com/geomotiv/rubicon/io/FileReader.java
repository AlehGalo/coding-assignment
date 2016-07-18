package com.geomotiv.rubicon.io;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;

/**
 * Created by Oleg on 7/18/16.
 */
@AllArgsConstructor
public abstract class FileReader<T> implements ResourceReader<T> {

    @Getter(AccessLevel.PROTECTED)
    private File file;

}
