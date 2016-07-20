package com.geomotiv.rubicon.utils;

/**
 * Created by Oleg on 7/19/16.
 */
public final class FileUtils {

    private FileUtils() {
    }

    public static String getFileExtension(String fileName) {
        Assert.notEmptyOrNull(fileName);
        int index = fileName.lastIndexOf('.');
        if (index >= 0 && index < fileName.length())
            return fileName.substring(index + 1);
        return "";
    }
}
