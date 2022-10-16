package com.example.schoolapp.util;

import com.example.schoolapp.dto.RequestWrapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @Author Nitesh Poudel
 */
@Slf4j
public class JsonUtil {

    @Autowired
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public static String getString(Object vendorResult) {
        try {
            String rawString = objectMapper.writeValueAsString(vendorResult);
            return rawString;
        } catch (JsonProcessingException ex) {
            log.error("Exception ", ex);
        }
        return "";
    }

    public static <T> T get(String content, Class clazz) throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException {
        return (T) objectMapper.readValue(content, clazz);
    }
}
