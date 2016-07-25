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
 * <p>Class that run the program contains main methon.</p>
 * <p>
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
public class Application {

    /**
     * Saving statistics regarding files processing number.
     */
    private static int counterOrUnreadFiles = 0, totalFiles = 0;

    /**
     * Time run holder.
     */
    private static long timeTaken;

    /**
     * Main class for running the application with all input parameters.
     *
     * @param args array of strings
     */
    public static void main(String args[]) {
        long startTimeOfExecution = System.currentTimeMillis();
        if (validateInputParameters(args)) {
            String pathToDirectory = args[0];
            String outputFile = args[1];
            List<SitesKeywordedResult> sitesKeywordedResult;
            try {
                sitesKeywordedResult =
                        new DirectoryReader().readResource(pathToDirectory).stream()
                                .map(Application::extractPath).filter(a -> !StringUtils.isEmptyOrNull(a.getCollectionId()))
                                .collect(Collectors.toList());
                new JSONFileWriter(new File(outputFile)).write(sitesKeywordedResult);
                timeTaken = System.currentTimeMillis() - startTimeOfExecution;
            } catch (RubiconException e) {
                printErrorMessage(e);
            }
            printStatistics();
        } else {
            printRules();
        }
    }

    /**
     * Extract path. Read all resources and keep the results in SiteKeywordedResult object.
     *
     * @param a - Path
     * @return execution result or empty site keyworded result
     */
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

    /**
     * Validation of input parameters
     *
     * @param args - arguments to be validated
     * @return true/false
     */
    private static boolean validateInputParameters(String args[]) {
        boolean result = true;
        if (args.length < 2) {
            result = false;
        }
        return result;
    }

    /**
     * Print requirements for program arguments
     */
    private static void printRules() {
        System.out.println("===================== No enough of arguments ===============================");
        System.out.println("Two arguments are required for correct program execution");
        System.out.println(" > 1. Path to directory that consists of files for processing");
        System.out.println(" > 2. Path to directory that will be populated with execution results");
        System.out.println("============================================================================");

    }

    /**
     * Printing error messaage of application.
     *
     * @param e - error occured.
     */
    private static void printErrorMessage(RubiconException e) {
        System.out.println("Error Occured > " + e.getMessage());
    }

    /**
     * Prints statistics
     */
    private static void printStatistics() {
        System.out.println("===================== Statistics =====================");
        System.out.println("> Unread files: " + counterOrUnreadFiles);
        System.out.println("> Total files: " + totalFiles);
        System.out.println("Time took: " + timeTaken + " ms");
    }

}
