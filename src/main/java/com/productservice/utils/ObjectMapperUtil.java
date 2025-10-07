package com.productservice.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {
    // Reuse ObjectMapper - thread-safe after configuration
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static <T> void updateObject(Object source, T target) throws JsonMappingException {
        MAPPER.updateValue(target, source);
    }
}
