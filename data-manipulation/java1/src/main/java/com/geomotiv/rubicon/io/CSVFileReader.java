package com.geomotiv.rubicon.io;

import com.geomotiv.rubicon.domain.Site;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleg on 7/18/16.
 */
public class CSVFileReader extends FileReader<List<Site>> {

    public CSVFileReader(File file) {
        super(file);
    }

    @Override
    public List<Site> readResource() {
        List<Site> listOfSites = new ArrayList<>();
        Reader in = null;
        try {
            in = new java.io.FileReader(getFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CSVParser records = null;
        try {
            records = CSVFormat.RFC4180.withFirstRecordAsHeader().withIgnoreEmptyLines().parse(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        records.getHeaderMap()
        for (CSVRecord record : records) {
//            record.toMap()
            Site site = new Site();
            String id = record.get("id");
            String name = record.get("name");
            String isMobile = record.get("is mobile");
            String score = record.get("score");
            site.setId(Integer.valueOf(id));
            site.setName(name);
            site.setMobile("1".equals(isMobile) ? true : false);
            site.setScore(Float.valueOf(score));
            listOfSites.add(site);
        }
        return listOfSites;
    }
}