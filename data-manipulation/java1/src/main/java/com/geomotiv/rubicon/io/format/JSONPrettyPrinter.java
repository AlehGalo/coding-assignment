package com.geomotiv.rubicon.io.format;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.geomotiv.rubicon.utils.StringUtils;

import java.io.IOException;

/**
 * <p>Extension of minimal pretty printer to be able to print one JSON object per line.</p>
 * <p>
 * <p>Copyright © 2016 Rubicon Project, All rights reserved.</p>
 */
public class JSONPrettyPrinter extends MinimalPrettyPrinter {

    @Override
    public void writeStartObject(JsonGenerator jg) throws IOException {
        jg.writeRaw(StringUtils.NEW_LINE);
        super.writeStartObject(jg);
    }
}
