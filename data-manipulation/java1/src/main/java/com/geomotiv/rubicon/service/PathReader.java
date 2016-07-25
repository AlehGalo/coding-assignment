package com.geomotiv.rubicon.service;

import com.geomotiv.rubicon.domain.Site;
import com.geomotiv.rubicon.domain.SiteKeyworded;
import com.geomotiv.rubicon.domain.SitesKeywordedResult;
import com.geomotiv.rubicon.exception.RubiconException;
import com.geomotiv.rubicon.io.ResourceReader;
import com.geomotiv.rubicon.utils.SiteIntantiationUtils;
import lombok.Setter;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.geomotiv.rubicon.domain.SupportedFileTypes.getFileTypeByExtension;
import static com.geomotiv.rubicon.utils.FileUtils.getFileExtension;
import static com.geomotiv.rubicon.utils.FileUtils.getFileName;

/**
 * <p>Reader for path resource.</p>
 * <p>
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
public class PathReader implements ResourceReader<SitesKeywordedResult, Path> {

    @Setter
    private Transformer transformable = new Transformer(new PlainKeywordService());

    public SitesKeywordedResult readResource(Path path) throws RubiconException {
        Objects.requireNonNull(path);
        String fileName = getFileName(path);
        String extension = getFileExtension(fileName);
        List<SiteKeyworded> list = new FileReaderFactory(getFileTypeByExtension(extension)).createObject().
                readResource(path).stream().map(this::createKeywordedSite).collect(Collectors.toList());
        return new SitesKeywordedResult(fileName, list);
    }

    private String getProcessResult(Object object) {
        Objects.requireNonNull(transformable);
        transformable.setObjectToProcess(object);
        transformable.transform();
        return transformable.getOperationResult();
    }

    private SiteKeyworded createKeywordedSite(Site a) {
        SiteKeyworded siteKeyworded = SiteIntantiationUtils.createSiteKeywordedFromSite(a);
        siteKeyworded.setKeywords(getProcessResult(a));
        return siteKeyworded;
    }
}
