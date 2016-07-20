package com.geomotiv.rubicon.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleg on 7/18/16.
 */
@JsonPropertyOrder({"id", "name", "mobile", "keywords", "score"})
public class SiteKeyworded extends Site {

    @Getter
    @Setter
    private String keywords;

    public static final SiteKeyworded instantiateSiteKeyworded(Site site) {
        SiteKeyworded siteKeyworded = new SiteKeyworded();
        siteKeyworded.setMobile(site.isMobile());
        siteKeyworded.setScore(site.getScore());
        siteKeyworded.setName(site.getName());
        siteKeyworded.setId(site.getId());
        return siteKeyworded;
    }

}
