package com.geomotiv.rubicon.service;

import com.geomotiv.rubicon.domain.Site;
import com.geomotiv.rubicon.domain.SiteKeyworded;
import com.geomotiv.rubicon.domain.SitesKeywordedResult;
import com.geomotiv.rubicon.domain.SupportedFileTypes;
import com.geomotiv.rubicon.exception.RubiconException;
import com.geomotiv.rubicon.exception.RubiconIOException;
import com.geomotiv.rubicon.exception.RubiconMissedReaderException;
import com.geomotiv.rubicon.io.ResourceReader;
import com.geomotiv.rubicon.utils.Assert;
import com.geomotiv.rubicon.utils.FileUtils;
import com.geomotiv.rubicon.utils.SiteIntantiationUtils;
import lombok.Setter;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static com.geomotiv.rubicon.domain.SupportedFileTypes.getFileTypeByExtension;
import static com.geomotiv.rubicon.utils.FileUtils.getFileExtension;
import static com.geomotiv.rubicon.utils.FileUtils.getFileName;

/**
 * Created by Oleg on 7/18/16.
 */
public class PathReader implements ResourceReader<SitesKeywordedResult, Path> {

    private FileReaderFactory fileReaderFactory = new FileReaderFactory();

    @Setter
    private Transformer transformable = new Transformer(new PlainKeywordService());

    public SitesKeywordedResult readResource(Path path) throws RubiconException {
        Assert.notNull(path);
        String fileName = getFileName(path);
        String extension = getFileExtension(fileName);
        List<SiteKeyworded> list = fileReaderFactory.getFileReader(getFileTypeByExtension(extension)).
                readResource(path.toFile()).stream().map(this::createKeywordedSite).collect(Collectors.toList());
        return new SitesKeywordedResult(fileName, list);
    }

    private String getProcessResult(Object object) {
        Assert.notNull(transformable);
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
