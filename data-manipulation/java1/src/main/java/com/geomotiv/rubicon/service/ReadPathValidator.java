package com.geomotiv.rubicon.service;

import com.geomotiv.rubicon.utils.FileUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.nio.file.Path;

/**
 * <p>.</p>
 *
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
@AllArgsConstructor
public class ReadPathValidator implements Validator {

    @Getter(AccessLevel.PACKAGE)
    private Path path;

    @Override
    public boolean validate() {
        return path != null ? FileUtils.isReadPermission(path) : false;
    }

}
