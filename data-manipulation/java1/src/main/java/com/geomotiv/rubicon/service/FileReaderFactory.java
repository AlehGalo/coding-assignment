package com.geomotiv.rubicon.service;

import com.geomotiv.rubicon.domain.Site;
import com.geomotiv.rubicon.domain.SupportedFileTypes;
import com.geomotiv.rubicon.exception.RubiconMissedReaderException;
import com.geomotiv.rubicon.io.CSVFileReader;
import com.geomotiv.rubicon.io.JSONPathReader;
import com.geomotiv.rubicon.io.ResourceReader;
import lombok.AllArgsConstructor;

import java.nio.file.Path;
import java.util.List;

/**
 * <p>Creation of Resource readers based on supported file types.</p>
 * <p>
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
@AllArgsConstructor
public class FileReaderFactory implements Factory<ResourceReader<List<Site>, Path>> {

    private SupportedFileTypes fileType;

    @Override
    public ResourceReader<List<Site>, Path> createObject() throws RubiconMissedReaderException {
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
