package com.geomotiv.rubicon.service;

import com.geomotiv.rubicon.domain.SitesKeywordedResult;
import com.geomotiv.rubicon.exception.RubiconException;
import com.geomotiv.rubicon.utils.Assert;
import com.geomotiv.rubicon.utils.FileUtils;
import lombok.AllArgsConstructor;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Oleg on 7/21/16.
 */
@AllArgsConstructor
public class ReadPathValidator implements Validator {

    private Path path;

    @Override
    public boolean validate() {
        return path != null ? FileUtils.isReadPermission(path) && Files.isDirectory(path) : false;
    }

}
