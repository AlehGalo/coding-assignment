package com.geomotiv.rubicon.io;

import com.geomotiv.rubicon.domain.CSVHeaders;
import com.geomotiv.rubicon.domain.Site;
import com.geomotiv.rubicon.exception.RubiconIOException;
import com.geomotiv.rubicon.service.CSVRecordsExtractor;
import lombok.Setter;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

/**
 * <p>CSV File reader of any CSV Stream reader.</p>
 * <p>
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
public class CSVFileReader implements ResourceReader<List<Site>, Path> {


    @Setter
    private CSVStreamReader streamReader = new CSVStreamReader(CSVHeaders.class, new CSVRecordsExtractor());

    @Override
    public List<Site> readResource(Path path) throws RubiconIOException {
        Objects.requireNonNull(path);
        try (FileReader fileReader = new FileReader(path.toFile())) {
            Objects.requireNonNull(streamReader);
            return streamReader.readResource(fileReader);
        } catch (IOException e) {
            throw new RubiconIOException(e.getMessage(), e);
        }
    }
}