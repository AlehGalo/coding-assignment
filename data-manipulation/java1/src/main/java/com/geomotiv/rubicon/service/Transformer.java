package com.geomotiv.rubicon.service;

import com.geomotiv.rubicon.utils.Assert;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import rubiconproject.KeywordService;

/**
 * Created by Oleg on 7/21/16.
 */
public class Transformer implements Transformable {

    private KeywordService keywordService;

    @Setter
    private Object objectToProcess;

    @Getter
    @Setter(AccessLevel.PRIVATE)
    private String operationResult;

    public Transformer(KeywordService keywordService) {
        this.keywordService = keywordService;
    }

    @Override
    public void transform() {
        Assert.notNull(keywordService);
        setOperationResult(keywordService.resolveKeywords(objectToProcess));
    }
}
