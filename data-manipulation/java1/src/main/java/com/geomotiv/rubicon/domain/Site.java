package com.geomotiv.rubicon.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonRootName("site")
public class Site implements Serializable {

    @JsonProperty("site_id")
    private Integer id;

    private String name;

    @JsonProperty("mobile")
    private boolean isMobile;

    private float score;
}
