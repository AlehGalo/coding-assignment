package com.geomotiv.rubicon.utils;

import com.geomotiv.rubicon.domain.CSVHeaders;
import com.geomotiv.rubicon.domain.Site;
import com.geomotiv.rubicon.domain.SiteKeyworded;
import org.apache.commons.csv.CSVRecord;

import static com.geomotiv.rubicon.utils.StringUtils.*;

/**
 * Created by Oleg on 7/20/16.
 */
public final class SiteIntantiationUtils {

    private SiteIntantiationUtils() {
    }

    public static SiteKeyworded instantiateSiteKeyworded(Site site) {
        Assert.notNull(site);
        SiteKeyworded siteKeyworded = new SiteKeyworded();
        siteKeyworded.setMobile(site.isMobile());
        siteKeyworded.setScore(site.getScore());
        siteKeyworded.setName(site.getName());
        siteKeyworded.setId(site.getId());
        return siteKeyworded;
    }

    public static Site createSiteFromRecord(CSVRecord record) {
        Assert.notNull(record);
        Site site = new Site();
        site.setId(getIntegerFromString(record.get(CSVHeaders.id)));
        site.setName(record.get(CSVHeaders.name));
        site.setMobile(getBooleanFromInt(record.get(CSVHeaders.is_mobile)));
        site.setScore(getFloatFromString(record.get(CSVHeaders.score)));
        return site;
    }

}
