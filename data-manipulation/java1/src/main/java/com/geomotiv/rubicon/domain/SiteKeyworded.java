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
}
