package com.geomotiv.rubicon.service;

import com.geomotiv.rubicon.domain.Site;
import com.geomotiv.rubicon.domain.SiteKeyworded;
import com.geomotiv.rubicon.domain.SitesKeywordedResult;
import com.geomotiv.rubicon.domain.SupportedFileTypes;
import com.geomotiv.rubicon.exception.RubiconException;
import com.geomotiv.rubicon.utils.Assert;
import com.geomotiv.rubicon.utils.FileUtils;
import com.geomotiv.rubicon.utils.SiteIntantiationUtils;
import rubiconproject.KeywordService;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleg on 7/18/16.
 */
public class ReadData {

    private KeywordService keywordService = new PlainKeywordService();

    private FileReaderFactory fileReaderFactory = new FileReaderFactory();

    public SitesKeywordedResult readData(Path path) {
        Assert.notNull(path);
        List<Site> listOfSites = null;
        String fileName = FileUtils.getFileName(path);
        String extension = FileUtils.getFileExtension(fileName);

        try {
            listOfSites = fileReaderFactory.getFileReader(SupportedFileTypes.getFileTypeByExtension(extension)).
                    readResource(path.toFile());
        } catch (RubiconException e) {
            e.printStackTrace();
        }
        List<SiteKeyworded> listOfSitesGlobal = new ArrayList<>();
        for (Site s : listOfSites) {
            System.out.print(s.getId() + " ");
            System.out.println(s.getName());
            SiteKeyworded sk = SiteIntantiationUtils.instantiateSiteKeyworded(s);
            sk.setKeywords(keywordService.resolveKeywords(s));
            listOfSitesGlobal.add(sk);
        }
        SitesKeywordedResult sitesKeywordedResult1 = new SitesKeywordedResult();
        sitesKeywordedResult1.setSites(listOfSitesGlobal);
        sitesKeywordedResult1.setCollectionId(FileUtils.getFileName(path));
        return sitesKeywordedResult1;
    }
}
