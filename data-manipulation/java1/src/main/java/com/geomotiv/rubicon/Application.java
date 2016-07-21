package com.geomotiv.rubicon;

import com.geomotiv.rubicon.domain.Site;
import com.geomotiv.rubicon.domain.SiteKeyworded;
import com.geomotiv.rubicon.domain.SitesKeywordedResult;
import com.geomotiv.rubicon.exception.RubiconException;
import com.geomotiv.rubicon.exception.RubiconIOException;
import com.geomotiv.rubicon.io.DirectoryReader;
import com.geomotiv.rubicon.io.JSONFileWriter;
import com.geomotiv.rubicon.io.JSONPathReader;
import com.geomotiv.rubicon.service.PathReader;
import com.geomotiv.rubicon.service.PlainKeywordService;
import com.geomotiv.rubicon.service.Transformer;
import rubiconproject.KeywordService;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Oleg on 7/15/16.
 */
public class Application {

    private static JSONPathReader jsonPathReader = new JSONPathReader();

    private static int counterOrUnreadFiles = 0, totalFiles = 0;

    public static void main(String args[]) throws RubiconException {
        long start = System.currentTimeMillis();
        if (validateInputParameters(args)) {
            String pathToDirectory = args[0];
            String outputFile = args[1];




            PathReader pathReader = new PathReader();

//            pathReader.setTransformable(new Transformer(new KeywordService() {
//                @Override
//                public String resolveKeywords(Object site) {
//                    return "ANYTHING";
//                }
//            }));


            List<SitesKeywordedResult> sitesKeywordedResult;
            sitesKeywordedResult = new DirectoryReader().readResource(pathToDirectory)
                    .map(Path::getFileSystem);




                            /*new PathReader().readResource())
                    .collect(Collectors.toList());*/

            new JSONFileWriter(new File(outputFile)).write(sitesKeywordedResult);
            System.out.println(System.currentTimeMillis() - start + " ms");
            System.out.println(sitesKeywordedResult.size());
        }
    }

    private static final List<Site> extractPath(Path a) {
        try {
//            new Transformer(new PlainKeywordService()).
            return new PathReader().readResource(a);
        } catch (RubiconIOException e) {
            ++counterOrUnreadFiles;
            printErrorMessage(e);
        }
        return Collections.EMPTY_LIST;
    }

    private static boolean validateInputParameters(String args[]) {
        boolean result = true;
        if (args.length < 2) {
            printRules();
            result = false;
        }
        return result;
    }

    private static void printRules() {
        System.out.println("===================== No enough of arguments ===============================");
        System.out.println("Two arguments are required for correct program execution");
        System.out.println(" > 1. Path to directory that consists of files for processing");
        System.out.println(" > 2. Path to directory that will be populated with execution results");
        System.out.println("============================================================================");

    }

    private static void printErrorMessage(Exception e) {
        System.out.println("Error Occured > " + e.getMessage());
    }

    private static void printDirectoryAccessError() {
        System.out.println("Directory is not reachable.");
    }

    private static void printStatistics(){

    }

}
