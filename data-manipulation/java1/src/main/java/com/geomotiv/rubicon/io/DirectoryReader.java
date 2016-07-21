package com.geomotiv.rubicon.io;

import com.geomotiv.rubicon.exception.RubiconException;
import com.geomotiv.rubicon.service.ReadPathValidator;
import com.geomotiv.rubicon.utils.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by Oleg on 7/21/16.
 */
public class DirectoryReader implements ResourceReader<Stream<Path>, String> {

    @Override
    public Stream<Path> readResource(String s) throws RubiconException {
        Assert.notEmpty(s);
        Path path = Paths.get(s);
        if (new ReadPathValidator(path).validate()) {
            try {
                return Files.list(path);
            } catch (IOException e) {
                throw new RubiconException("Getting file list error");
            }
        } else {
            throw new RubiconException("Invalid path " + s);
        }
    }
}
