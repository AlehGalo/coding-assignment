package com.geomotiv.rubicon.service;

import com.geomotiv.rubicon.domain.Site;
import com.geomotiv.rubicon.domain.SupportedFileTypes;
import com.geomotiv.rubicon.io.CSVFileReader;
import com.geomotiv.rubicon.io.JSONFileReader;
import com.geomotiv.rubicon.io.ResourceReader;

import java.io.File;
import java.util.List;

/**
 * Created by Oleg on 7/18/16.
 */
public class FileReaderFactory {

    public ResourceReader<List<Site>, File> getFileReader(SupportedFileTypes fileType) {
        switch (fileType) {
            case CSV:
                return new CSVFileReader();
            case JSON:
                return new JSONFileReader();
            default:
                throw new IllegalArgumentException("Unsupported file type");
        }
    }
}
