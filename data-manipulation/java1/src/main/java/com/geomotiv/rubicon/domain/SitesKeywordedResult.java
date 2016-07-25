package com.geomotiv.rubicon.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Entity that contains the results of aggregation of data.</p>
 *
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
@Setter
@Getter
@AllArgsConstructor
public class SitesKeywordedResult implements Serializable{

    private String collectionId;

    private List<SiteKeyworded> sites;
}
