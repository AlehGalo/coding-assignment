package com.geomotiv.rubicon.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Oleg on 7/18/16.
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
