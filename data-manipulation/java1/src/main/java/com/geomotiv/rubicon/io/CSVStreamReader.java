package com.geomotiv.rubicon.io;

import com.geomotiv.rubicon.domain.Site;
import com.geomotiv.rubicon.exception.RubiconException;
import com.geomotiv.rubicon.exception.RubiconIOException;
import com.geomotiv.rubicon.service.CSVParserFactory;
import com.geomotiv.rubicon.service.Extractable;
import com.geomotiv.rubicon.utils.Assert;
import org.apache.commons.csv.CSVRecord;

import java.io.Reader;
import java.util.List;

/**
 * Created by Oleg on 7/19/16.
 */
public class CSVStreamReader implements ResourceReader<List<Site>, Reader> {

    private Iterable<CSVRecord> iterable;

    private Class<? extends Enum<?>> header;

    private Extractable<List<Site>, CSVRecord> extractor;

    public CSVStreamReader(Class<? extends Enum<?>> headerEnum, Extractable<List<Site>, CSVRecord> extractor) {
        Assert.notNull(headerEnum);
        Assert.notNull(extractor);
        this.header = headerEnum;
        this.extractor = extractor;
    }

    @Override
    public List<Site> readResource(Reader fileReader) throws RubiconException {
        Assert.notNull(fileReader);
        instantiateParserFactory(fileReader);
        return extractRecords();
    }

    private void instantiateParserFactory(Reader fileReader) throws RubiconIOException {
        iterable = new CSVParserFactory().createParser(header, fileReader);
    }

    private List<Site> extractRecords() {
        return extractor.extract(iterable);
    }
}
