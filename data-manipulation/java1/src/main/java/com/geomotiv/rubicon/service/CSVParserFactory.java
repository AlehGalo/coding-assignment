package com.geomotiv.rubicon.service;

import com.geomotiv.rubicon.utils.Assert;
import lombok.Setter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by Oleg on 7/19/16.
 */
public class CSVParserFactory {

    @Setter
    private CSVFormat csvFormat;

    private static final CSVFormat DEFAULT_CSV_FORMAT = CSVFormat.RFC4180.withFirstRecordAsHeader().withIgnoreEmptyLines();

    public CSVParser createParser(final Class<? extends Enum<?>> headerEnum, final Reader reader) {
        Assert.notNull(headerEnum);
        try {
            return getParserBasedOnHeader(headerEnum).parse(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("asdf");
    }

    private CSVFormat getCsvFormat() {
        return csvFormat == null ? DEFAULT_CSV_FORMAT : csvFormat;
    }

    private CSVFormat getParserBasedOnHeader(final Class<? extends Enum<?>> headerEnum) {
        return getCsvFormat().withHeader(headerEnum);
    }
}
