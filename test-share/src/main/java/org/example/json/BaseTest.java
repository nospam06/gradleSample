package org.example.json;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.list.BusinessService;
import org.example.utilities.JsonService;

import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    private final Map<String, Object> beanMap = new HashMap<>();

    protected ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.setDefaultPrettyPrinter(new DefaultPrettyPrinter());
        objectMapper.registerModules(new JavaTimeModule());
        return objectMapper;
    }

    @SuppressWarnings("unchecked")
    protected <T> T getBean(Class<T> cls) {
        return (T) beanMap.computeIfAbsent(cls.getSimpleName(), id -> createBean(cls));
    }

    @SuppressWarnings("unchecked")
    private <T> T createBean(Class<T> cls) {
        if (cls.equals(JsonService.class)) {
            return (T) createJsonService();
        }
        if (cls.equals(BusinessService.class)) {
            return (T) createBusinessService();
        }
        return null;
    }

    protected JsonService createJsonService() {
        return new JsonService(createObjectMapper());
    }

    protected BusinessService createBusinessService() {
        return new BusinessService(createJsonService());
    }
}