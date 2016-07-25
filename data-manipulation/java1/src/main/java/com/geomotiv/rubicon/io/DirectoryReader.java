package com.geomotiv.rubicon.io;

import com.geomotiv.rubicon.exception.RubiconException;
import com.geomotiv.rubicon.service.ReadDirectoryValidator;
import com.geomotiv.rubicon.utils.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Reader of directories that also validates it for Read permissions.</p>
 *
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
public class DirectoryReader implements ResourceReader<List<Path>, String> {

    @Override
    public List<Path> readResource(String s) throws RubiconException {
        Assert.notEmpty(s);
        Path path = Paths.get(s);
        if (new ReadDirectoryValidator(path).validate()) {
            try {
                return Files.list(path).map(a -> a).collect(Collectors.toList());
            } catch (IOException e) {
                throw new RubiconException("Getting file list error");
            }
        } else {
            throw new RubiconException("Invalid path " + s);
        }
    }
}
