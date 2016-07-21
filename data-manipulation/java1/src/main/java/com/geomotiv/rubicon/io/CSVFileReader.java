package com.geomotiv.rubicon.io;

import com.geomotiv.rubicon.domain.CSVHeaders;
import com.geomotiv.rubicon.domain.Site;
import com.geomotiv.rubicon.exception.RubiconIOException;
import com.geomotiv.rubicon.service.CSVRecordsExtractor;
import com.geomotiv.rubicon.utils.Assert;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by Oleg on 7/18/16.
 */
public class CSVFileReader implements ResourceReader<List<Site>, Path> {


    private CSVStreamReader streamReader = new CSVStreamReader(CSVHeaders.class, new CSVRecordsExtractor());

    @Override
    public List<Site> readResource(Path path) throws RubiconIOException {
        Assert.notNull(file);
        try (FileReader fileReader = new FileReader(path)) {
            return streamReader.readResource(fileReader);
        } catch (IOException e) {
            throw new RubiconIOException(e.getMessage(), e);
        }
    }
}