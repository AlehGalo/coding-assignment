package com.geomotiv.rubicon.service;

import com.geomotiv.rubicon.domain.Site;
import com.geomotiv.rubicon.domain.SupportedFileTypes;
import com.geomotiv.rubicon.io.CSVFileReader;
import com.geomotiv.rubicon.io.FileReader;
import com.geomotiv.rubicon.io.JSONFileReader;

import java.io.File;
import java.util.List;

/**
 * Created by Oleg on 7/18/16.
 */
public class FileReaderFactory {

    private File file;

    public FileReaderFactory(File file){
        this.file = file;
    }

    public FileReader<List<Site>> getFileReader(SupportedFileTypes fileType) {
        switch (fileType) {
            case CSV:
                return new CSVFileReader(file);
            case JSON:
                return new JSONFileReader(file);
            default:
                throw new IllegalArgumentException("Unsupported file type");
        }
    }
}
