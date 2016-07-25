package com.geomotiv.rubicon.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import rubiconproject.KeywordService;

import java.util.Objects;

/**
 * <p>.</p>
 *
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
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
        Objects.requireNonNull(keywordService);
        setOperationResult(keywordService.resolveKeywords(objectToProcess));
    }
}
