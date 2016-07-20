package com.geomotiv.rubicon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.geomotiv.rubicon.domain.Site;
import com.geomotiv.rubicon.domain.SiteKeyworded;
import com.geomotiv.rubicon.domain.SitesKeywordedResult;
import com.geomotiv.rubicon.domain.SupportedFileTypes;
import com.geomotiv.rubicon.exception.RubiconException;
import com.geomotiv.rubicon.service.FileReaderFactory;
import com.geomotiv.rubicon.service.ReadData;
import com.geomotiv.rubicon.utils.FileUtils;
import com.geomotiv.rubicon.utils.SiteIntantiationUtils;
import rubiconproject.KeywordService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleg on 7/15/16.
 */
public class Application {

    private static KeywordService keywordService = new KeywordService() {
        @Override
        public String resolveKeywords(Object site) {
            return "abc,asdf,ggasdf";
        }
    };

    public static void main(String args[]) throws IOException {
        long start = System.currentTimeMillis();
        if (args.length < 2) {
            throw new IllegalArgumentException("No enough of arguments");
        }
        String pathToDirectory = args[0];
        String outputFile = args[1];
        Path pathToDir = Paths.get(pathToDirectory);
        boolean isDirectory = Files.isDirectory(pathToDir);
        List<SitesKeywordedResult> sitesKeywordedResult = new ArrayList<>();
        if (isDirectory) {
            FileReaderFactory factory = new FileReaderFactory();
            Files.list(pathToDir).forEach(a -> {
                sitesKeywordedResult.add(new ReadData().readData(a));
            });
        }
        System.out.println(System.currentTimeMillis() - start + " ms");
        System.out.println(sitesKeywordedResult.size());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new FileOutputStream(new File(outputFile)), sitesKeywordedResult);
//        System.out.println(listOfSitesGlobal.size());
    }


}
