package com.geomotiv.rubicon.service;

import com.geomotiv.rubicon.domain.Site;
import com.geomotiv.rubicon.utils.Assert;
import org.apache.commons.csv.CSVRecord;

import java.util.List;
import java.util.stream.Collectors;

import static com.geomotiv.rubicon.utils.SiteIntantiationUtils.createSiteFromRecord;
import static java.util.stream.StreamSupport.stream;

/**
 * Created by Oleg on 7/20/16.
 */
public class SiteExtractor implements Extractable<List<Site>, CSVRecord> {

    @Override
    public List<Site> extract(Iterable<CSVRecord> iterable) {
        Assert.notNull(iterable);
        return stream(iterable.spliterator(), false).
                map(a -> createSiteFromRecord(a)).collect(Collectors.toList());
    }
}
