package com.geomotiv.rubicon.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Oleg on 7/18/16.
 */
public enum SupportedFileTypes {
    CSV("csv"), JSON("json"), UNSUPPORTED;

    private Set<String> extensions;

    SupportedFileTypes(String ... args){
        extensions = new HashSet<>(args.length);
        extensions.addAll(Arrays.asList(args));
    }

    public static SupportedFileTypes getFileTypeByExtension(String source){
        SupportedFileTypes types[] = SupportedFileTypes.values();
        for(SupportedFileTypes type: types){
            if(type.extensions.contains(source)){
                return type;
            }
        }
        return UNSUPPORTED;
    }
}
