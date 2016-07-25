package com.geomotiv.rubicon.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>Top level entity that consists of data related to site.</p>
 *
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
@Getter
@Setter
public class Site implements Serializable {

    private Integer id;

    private String name;

    @JsonProperty("mobile")
    private boolean isMobile;

    private float score;

    @JsonGetter("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("site_id")
    public void setSiteId(Integer id) {
        this.id = id;
    }
}
