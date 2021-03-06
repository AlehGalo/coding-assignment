package com.geomotiv.rubicon.service;

import com.geomotiv.rubicon.exception.RubiconIOException;
import lombok.AccessLevel;
import lombok.Setter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.IOException;
import java.io.Reader;
import java.util.Objects;

/**
 * <p>.</p>
 * <p>
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
public class CSVParserFactory implements Factory<CSVParser> {

    @Setter
    private CSVFormat csvFormat;

    private static final CSVFormat DEFAULT_CSV_FORMAT = CSVFormat.RFC4180.withFirstRecordAsHeader().withIgnoreEmptyLines();

    @Setter(AccessLevel.PRIVATE)
    private Class<? extends Enum<?>> headerEnum;

    @Setter(AccessLevel.PRIVATE)
    private Reader reader;


    public CSVParserFactory(Class<? extends Enum<?>> headerEnum, Reader reader) {
        Objects.requireNonNull(headerEnum);
        Objects.requireNonNull(reader);
        setHeaderEnum(headerEnum);
        setReader(reader);
    }


    private CSVFormat getCsvFormat() {
        return csvFormat == null ? DEFAULT_CSV_FORMAT : csvFormat;
    }

    private CSVFormat getParserBasedOnHeader(final Class<? extends Enum<?>> headerEnum) {
        return getCsvFormat().withHeader(headerEnum);
    }

    @Override
    public CSVParser createObject() throws RubiconIOException {
        Objects.requireNonNull(headerEnum);
        try {
            return getParserBasedOnHeader(headerEnum).parse(reader);
        } catch (IOException e) {
            throw new RubiconIOException(e.getMessage(), e);
        }
    }
}