package com.example.studentapp.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Nitesh Poudel
 */
@Slf4j
public class JsonUtil {

    @Autowired
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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

}
