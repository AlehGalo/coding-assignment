package com.geomotiv.rubicon.io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geomotiv.rubicon.domain.Site;
import com.geomotiv.rubicon.exception.RubiconException;
import com.geomotiv.rubicon.exception.RubiconIOException;
import com.geomotiv.rubicon.utils.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Oleg on 7/18/16.
 */

public class JSONFileReader implements ResourceReader<List<Site>, File> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Site> readResource(File file) throws RubiconException {
        Assert.notNull(file);
        try {
            return objectMapper.readValue(file, new TypeReference<List<Site>>() {
            });
        } catch (IOException e) {
            throw new RubiconIOException(e.getMessage(), e);
        }
    }
}
