package com.geomotiv.rubicon;

import com.geomotiv.rubicon.domain.SitesKeywordedResult;
import com.geomotiv.rubicon.exception.RubiconException;
import com.geomotiv.rubicon.io.DirectoryReader;
import com.geomotiv.rubicon.io.JSONFileWriter;
import com.geomotiv.rubicon.service.PathReader;
import com.geomotiv.rubicon.utils.SiteIntantiationUtils;
import com.geomotiv.rubicon.utils.StringUtils;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>.</p>
 * <p>
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
public class Application {


    private static int counterOrUnreadFiles = 0, totalFiles = 0;

    private static long timeTaken;

    public static void main(String args[]) throws RubiconException {
        long startTimeOfExecution = System.currentTimeMillis();
        if (validateInputParameters(args)) {
            String pathToDirectory = args[0];
            String outputFile = args[1];

            List<SitesKeywordedResult> sitesKeywordedResult;
            sitesKeywordedResult =
                    new DirectoryReader().readResource(pathToDirectory).stream()
                            .map(Application::extractPath).filter(a -> !StringUtils.isEmptyOrNull(a.getCollectionId()))
                            .collect(Collectors.toList());


            new JSONFileWriter(new File(outputFile)).write(sitesKeywordedResult);
            timeTaken = System.currentTimeMillis() - startTimeOfExecution;
            printStatistics();
        }
    }

    private static SitesKeywordedResult extractPath(Path a) {
        try {
            return new PathReader().readResource(a);
        } catch (RubiconException e) {
            ++counterOrUnreadFiles;
            printErrorMessage(e);
        } finally {
            ++totalFiles;
        }
        return SiteIntantiationUtils.getEmptySitesKeywordedResult();
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

    private static void printErrorMessage(RubiconException e) {
        System.out.println("Error Occured > " + e.getMessage());
    }

    private static void printStatistics() {
        System.out.println("===================== Statistics =====================");
        System.out.println("> Unread files: " + counterOrUnreadFiles);
        System.out.println("> Total files: " + totalFiles);
        System.out.println("Time took: " + timeTaken + " ms");
    }

}
