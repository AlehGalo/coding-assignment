package com.geomotiv.rubicon.io;

import com.geomotiv.rubicon.domain.CSVHeaders;
import com.geomotiv.rubicon.domain.Site;
import com.geomotiv.rubicon.exception.RubiconException;
import com.geomotiv.rubicon.exception.RubiconIOException;
import com.geomotiv.rubicon.service.SiteExtractor;
import com.geomotiv.rubicon.utils.Assert;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by Oleg on 7/18/16.
 */
public class CSVFileReader implements ResourceReader<List<Site>, File> {


    private CSVStreamReader streamReader = new CSVStreamReader(CSVHeaders.class, new SiteExtractor());

    @Override
    public List<Site> readResource(File file) throws RubiconException {
        Assert.notNull(file);
        try (FileReader fileReader = new FileReader(file)) {
            return streamReader.readResource(fileReader);
        } catch (IOException e) {
            throw new RubiconIOException(e.getMessage(), e);
        }
    }
}