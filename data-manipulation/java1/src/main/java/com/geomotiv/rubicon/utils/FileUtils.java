package com.geomotiv.rubicon.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Function;

/**
 * <p>Utility class for manipulation of files.</p>
 *
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
public final class FileUtils {

    private FileUtils() {
    }

    public static String getFileExtension(String fileName) {
        Assert.notEmpty(fileName);
        int index = fileName.lastIndexOf('.');
        if (index >= 0 && index < fileName.length())
            return fileName.substring(index + 1);
        return StringUtils.EMPTY;
    }

    public static String getFileName(Path source) {
        Objects.requireNonNull(source);
        return source.getFileName().toString();
    }

    public static boolean isReadPermission(Path p) {
        return isPermission(p, Files::isReadable);
    }

    public static boolean isWritePermission(Path p) {
        return isPermission(p, Files::isWritable);
    }

    private static boolean isPermission(Path p, Function<Path, Boolean> function) {
        Objects.requireNonNull(p);
        Objects.requireNonNull(function);
        return function.apply(p);
    }
}
