package org.example.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object body, Boolean prettyPrint) {
        try {
            if (body == null) {
                return (String) body;
            }
            if (Boolean.TRUE.equals(prettyPrint)) {
                return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);
            } else {
                return objectMapper.writeValueAsString(body);
            }
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
