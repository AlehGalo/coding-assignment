package com.geomotiv.rubicon.service;

import com.geomotiv.rubicon.domain.Site;
import org.apache.commons.csv.CSVRecord;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.geomotiv.rubicon.utils.SiteIntantiationUtils.createSiteFromRecord;
import static java.util.stream.StreamSupport.stream;

/**
 * <p>Extract iterable CSVRecord to site entity.</p>
 * <p>
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
public class CSVRecordsExtractor implements Extractable<List<Site>, CSVRecord> {

    @Override
    public List<Site> extract(Iterable<CSVRecord> iterable) {
        Objects.requireNonNull(iterable);
        return stream(iterable.spliterator(), false).
                map(a -> createSiteFromRecord(a)).collect(Collectors.toList());
    }
}
