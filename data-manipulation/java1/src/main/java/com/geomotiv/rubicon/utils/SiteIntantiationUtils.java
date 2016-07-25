package com.geomotiv.rubicon.utils;

import com.geomotiv.rubicon.domain.CSVHeaders;
import com.geomotiv.rubicon.domain.Site;
import com.geomotiv.rubicon.domain.SiteKeyworded;
import com.geomotiv.rubicon.domain.SitesKeywordedResult;
import org.apache.commons.csv.CSVRecord;

import java.util.Collections;
import java.util.Objects;

import static com.geomotiv.rubicon.utils.StringUtils.*;

/**
 * <p>Utility class for Site and it's children objects.</p>
 *
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
public final class SiteIntantiationUtils {

    private static SitesKeywordedResult NULL_OBJECT = new SitesKeywordedResult(StringUtils.EMPTY, Collections.EMPTY_LIST);

    private SiteIntantiationUtils() {
    }

    public static SiteKeyworded createSiteKeywordedFromSite(Site site) {
        Objects.requireNonNull(site);
        SiteKeyworded siteKeyworded = new SiteKeyworded();
        siteKeyworded.setMobile(site.isMobile());
        siteKeyworded.setScore(site.getScore());
        siteKeyworded.setName(site.getName());
        siteKeyworded.setId(site.getId());
        return siteKeyworded;
    }

    public static Site createSiteFromRecord(CSVRecord record) {
        Objects.requireNonNull(record);
        Site site = new Site();
        site.setId(getIntegerFromString(record.get(CSVHeaders.id)));
        site.setName(record.get(CSVHeaders.name));
        site.setMobile(getBooleanFromInt(record.get(CSVHeaders.is_mobile)));
        site.setScore(getFloatFromString(record.get(CSVHeaders.score)));
        return site;
    }

    public static SitesKeywordedResult getEmptySitesKeywordedResult() {
        return NULL_OBJECT;
    }

}
