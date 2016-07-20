package com.geomotiv.rubicon.io;

import com.geomotiv.rubicon.domain.CSVHeaders;
import com.geomotiv.rubicon.domain.Site;
import com.geomotiv.rubicon.service.CSVParserFactory;
import com.geomotiv.rubicon.utils.Assert;
import org.apache.commons.csv.CSVRecord;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static com.geomotiv.rubicon.utils.StringUtils.*;

/**
 * Created by Oleg on 7/19/16.
 */
public class CSVStreamReader implements ResourceReader<List<Site>, Reader> {

    private Iterable<CSVRecord> iterable;

    @Override
    public List<Site> readResource(Reader fileReader) {
        Assert.notNull(fileReader);
        instantiateParserFactory(fileReader);
        return extractRecords();
    }

    private Site createSiteFromRecord(CSVRecord record) {
        Site site = new Site();
        site.setId(getIntegerFromString(record.get(CSVHeaders.id)));
        site.setName(record.get(CSVHeaders.name));
        site.setMobile(getBooleanFromInt(record.get(CSVHeaders.is_mobile)));
        site.setScore(getFloatFromString(record.get(CSVHeaders.score)));
        return site;
    }

    private void instantiateParserFactory(Reader fileReader) {
        iterable = new CSVParserFactory().createParser(CSVHeaders.class, fileReader);
    }

    private List<Site> extractRecords() {
        List<Site> sites = new ArrayList<>();
        iterable.forEach(a -> {
            sites.add(createSiteFromRecord(a));
        });
        return sites;
    }
}
