package com.geomotiv.rubicon.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.geomotiv.rubicon.domain.SitesKeywordedResult;
import com.geomotiv.rubicon.exception.RubiconIOException;
import com.geomotiv.rubicon.utils.Assert;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Oleg on 7/21/16.
 */
@AllArgsConstructor
public class JSONFileWriter implements ResourceWriter<List<SitesKeywordedResult>> {

    private File outputFile;

    @Override
    public void write(List<SitesKeywordedResult> sitesKeywordedResult) throws RubiconIOException {
        Assert.notNull(sitesKeywordedResult);
        Assert.notNull(outputFile);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(outputFile);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileOutputStream, sitesKeywordedResult);
        } catch (IOException e) {
            throw new RubiconIOException(e.getMessage(), e);
        }
    }
}
