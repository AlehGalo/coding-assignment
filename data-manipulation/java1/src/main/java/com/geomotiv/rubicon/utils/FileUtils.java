package com.geomotiv.rubicon.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;

/**
 * Created by Oleg on 7/19/16.
 */
public final class FileUtils {

    private FileUtils() {
    }

    public static String getFileExtension(String fileName) {
        Assert.emptyOrNull(fileName);
        int index = fileName.lastIndexOf('.');
        if (index >= 0 && index < fileName.length())
            return fileName.substring(index + 1);
        return "";
    }

    public static String getFileName(Path source) {
        Assert.notNull(source);
        return source.getFileName().toString();
    }

    public static boolean isReadPermission(Path p) {
        return isPermission(p, Files::isReadable);
    }

    public static boolean isWritePermission(Path p) {
        return isPermission(p, Files::isWritable);
    }

    private static boolean isPermission(Path p, Function<Path, Boolean> function) {
        Assert.notNull(p);
        Assert.notNull(function);
        return function.apply(p);
    }
}
