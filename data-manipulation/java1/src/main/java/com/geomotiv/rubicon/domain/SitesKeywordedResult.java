package com.geomotiv.rubicon.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Oleg on 7/19/16.
 */
@Getter
@Setter
public class SitesKeywordedResult implements Serializable{

    private String collectionId;

    private List<SiteKeyworded> sites;
}
