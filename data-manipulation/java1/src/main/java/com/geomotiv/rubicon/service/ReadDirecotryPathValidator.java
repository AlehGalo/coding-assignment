package com.geomotiv.rubicon.service;

import com.geomotiv.rubicon.utils.Assert;
import lombok.AllArgsConstructor;

import java.nio.file.Path;
import java.util.List;

/**
 * Created by olegbogatyryev on 7/21/16.
 */
@AllArgsConstructor
@Deprecated
public class ReadDirecotryPathValidator implements Validator{

    private List<Path> pathList;

    @Override
    public boolean validate() {
        Assert.notNull(pathList);

        return false;
    }
}
