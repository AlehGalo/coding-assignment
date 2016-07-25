package com.geomotiv.rubicon.domain;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

/**
 * <p>All extensions of files that are supported by the system. One file type can support more then one file extension.</p>
 * <p>
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
public enum SupportedFileTypes {

    CSV("csv"), JSON("json"), UNSUPPORTED;

    private Set<String> extensions;

    SupportedFileTypes(String... args) {
        extensions = new HashSet<>(args.length);
        extensions.addAll(asList(args));
    }

    public static SupportedFileTypes getFileTypeByExtension(String source) {
        SupportedFileTypes types[] = SupportedFileTypes.values();
        for (SupportedFileTypes type : types) {
            if (type.extensions.contains(source)) {
                return type;
            }
        }
        return UNSUPPORTED;
    }
}
