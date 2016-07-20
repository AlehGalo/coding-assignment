package com.geomotiv.rubicon.io;

import com.geomotiv.rubicon.domain.Site;
import com.geomotiv.rubicon.utils.Assert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.util.List;

/**
 * Created by Oleg on 7/18/16.
 */
public class CSVFileReader implements ResourceReader<List<Site>, File> {

    private CSVStreamReader streamReader = new CSVStreamReader();

    @Override
    public List<Site> readResource(File file) {
        Assert.notNull(file);
        Reader in = null;
        try {
            in = new java.io.FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return streamReader.readResource(in);
    }


}