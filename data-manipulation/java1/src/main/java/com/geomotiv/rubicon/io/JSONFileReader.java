package com.geomotiv.rubicon.io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geomotiv.rubicon.domain.Site;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Oleg on 7/18/16.
 */

public class JSONFileReader extends FileReader<List<Site>> {

    public JSONFileReader(File file){
        super(file);
    }

    @Override
    public List<Site> readResource() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(getFile(), new TypeReference<List<Site>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
//        TODO: remove it
        return null;
    }
}
