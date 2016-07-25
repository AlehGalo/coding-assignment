package com.geomotiv.rubicon.service;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * <p>.</p>
 *
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
public class ReadDirectoryValidator extends ReadPathValidator{

    public ReadDirectoryValidator(Path path) {
        super(path);
    }

    @Override
    public boolean validate() {
        return super.validate() && Files.isDirectory(getPath());
    }
}
