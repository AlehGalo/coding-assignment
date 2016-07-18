package com.geomotiv.rubicon.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Oleg on 7/18/16.
 */
public class SiteScored extends Site{

    @Getter
    @Setter
    private List<String> keywords;

}
