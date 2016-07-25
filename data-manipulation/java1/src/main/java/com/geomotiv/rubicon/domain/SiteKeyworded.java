package com.geomotiv.rubicon.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>Extension of Site that contains extra info with keywords.</p>
 *
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
@JsonPropertyOrder({"id", "name", "mobile", "keywords", "score"})
public class SiteKeyworded extends Site {

    @Getter
    @Setter
    private String keywords;
}
