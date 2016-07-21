package com.geomotiv.rubicon.io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geomotiv.rubicon.domain.Site;
import com.geomotiv.rubicon.exception.RubiconIOException;
import com.geomotiv.rubicon.service.ReadPathValidator;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by Oleg on 7/18/16.
 */

public class JSONPathReader implements ResourceReader<List<Site>, Path> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Site> readResource(Path path) throws RubiconIOException {
        if (new ReadPathValidator(path).validate()) {
            try {
                return objectMapper.readValue(path.toFile(), new TypeReference<List<Site>>() {
                });
            } catch (IOException e) {
                throw new RubiconIOException(e.getMessage(), e);
            }
        }
        throw new RubiconIOException("Error path:" + path.toAbsolutePath());
    }
}
