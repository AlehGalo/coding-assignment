package com.geomotiv.rubicon.service;

import com.geomotiv.rubicon.utils.StringUtils;
import rubiconproject.KeywordService;

/**
 * <p>.</p>
 *
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
public class PlainKeywordService implements KeywordService {

    @Override
    public String resolveKeywords(Object site) {
        return StringUtils.EMPTY;
    }
}