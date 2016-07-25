package com.geomotiv.rubicon.io;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.geomotiv.rubicon.domain.SitesKeywordedResult;
import com.geomotiv.rubicon.exception.RubiconIOException;
import com.geomotiv.rubicon.io.format.JSONPrettyPrinter;
import com.oracle.javafx.jmx.json.JSONFactory;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * <p>Writer for JSON file.</p>
 * <p>
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
@AllArgsConstructor
public class JSONFileWriter implements ResourceWriter<List<SitesKeywordedResult>> {

    private File outputFile;

    @Override
    public void write(List<SitesKeywordedResult> sitesKeywordedResult) throws RubiconIOException {
        Objects.requireNonNull(sitesKeywordedResult);
        Objects.requireNonNull(outputFile);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)) {
            objectMapper.writer(instantiatePrettyPrinter()).writeValue(fileOutputStream, sitesKeywordedResult);
        } catch (IOException e) {
            throw new RubiconIOException(e.getMessage(), e);
        }
    }

    private PrettyPrinter instantiatePrettyPrinter() throws RubiconIOException {
        return new JSONPrettyPrinter();
    }
}
