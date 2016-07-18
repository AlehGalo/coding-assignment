package com.geomotiv.rubicon;

import com.geomotiv.rubicon.domain.Site;
import com.geomotiv.rubicon.domain.SupportedFileTypes;
import com.geomotiv.rubicon.service.FileReaderFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

/**
 * Created by Oleg on 7/15/16.
 */
public class Application {

    public static void main(String args[]) throws IOException {
        long start = System.currentTimeMillis();
        if (args.length < 2) {
            throw new IllegalArgumentException("No enough of arguments");
        }
        String pathToDirectory = args[0];
        String outputFile = args[1];
        Path pathToDir = Paths.get(pathToDirectory);
        boolean isDirectory = Files.isDirectory(pathToDir);
        if (isDirectory) {
            Files.list(pathToDir).forEach(a -> {
                String extension = getFileExtension(a.getFileName().toString());
                FileReaderFactory factory = new FileReaderFactory(a.toFile());

                SupportedFileTypes supportedFileTypes = SupportedFileTypes.getFileTypeByExtension(extension);

                List<Site> listOfSites = factory.getFileReader(supportedFileTypes).readResource();
                for (Site s : listOfSites) {
                    System.out.print(s.getId() + " ");
                    System.out.println(s.getName());
                }
            });
        }
        System.out.println(System.currentTimeMillis() - start + " ms");
    }

    private static final String getFileExtension(String fileName) {
        if (fileName != null) {
            int index = fileName.lastIndexOf('.');
            if (index > 0 && index < fileName.length())
                return fileName.substring(index + 1);
            return "";
        }
        throw new IllegalArgumentException();
    }
}
