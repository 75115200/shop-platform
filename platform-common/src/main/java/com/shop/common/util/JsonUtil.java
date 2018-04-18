package com.shop.common.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * json转换处理类
 * @author zzs
 */
public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();
    
    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
    public static String objToJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static <T> T jsonToBean(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static <T> T jsonToBean(String json, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static String objToJson(Object obj, String... ignoreFields) {
        SimpleBeanPropertyFilter propertiesFilter = SimpleBeanPropertyFilter.serializeAllExcept(ignoreFields);
        FilterProvider filters = new SimpleFilterProvider().addFilter("propertiesFilter", propertiesFilter);
        try {
            return mapper.writer(filters).writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("json转换异常", e);
        }
    }
}
