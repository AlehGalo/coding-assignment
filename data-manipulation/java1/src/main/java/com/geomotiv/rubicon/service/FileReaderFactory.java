package com.geomotiv.rubicon.service;

import com.geomotiv.rubicon.domain.Site;
import com.geomotiv.rubicon.domain.SupportedFileTypes;
import com.geomotiv.rubicon.exception.RubiconMissedReaderException;
import com.geomotiv.rubicon.io.CSVFileReader;
import com.geomotiv.rubicon.io.JSONPathReader;
import com.geomotiv.rubicon.io.ResourceReader;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by Oleg on 7/18/16.
 */
public class FileReaderFactory {

    public ResourceReader<List<Site>, Path> getFileReader(SupportedFileTypes fileType) throws RubiconMissedReaderException {
        switch (fileType) {
            case CSV:
                return new CSVFileReader();
            case JSON:
                return new JSONPathReader();
            default:
                throw new RubiconMissedReaderException("Unsupported file type " + fileType);
        }
    }
}
