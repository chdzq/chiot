package org.chdzq.common.core.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JsonUtil
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/21 11:41
 */
@Slf4j
public class JsonUtil {

    private static ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    /**
     * 初始化 objectMapper 属性
     * <p>
     * 通过这样的方式，使用 Spring 创建的 ObjectMapper Bean
     *
     * @param objectMapper ObjectMapper 对象
     */
    public static void init(ObjectMapper objectMapper) {
        JsonUtil.MAPPER = objectMapper;
    }

    @SneakyThrows
    public static String toJsonString(Object object) {
        return MAPPER.writeValueAsString(object);
    }

    @SneakyThrows
    public static byte[] toJsonByte(Object object) {
        return MAPPER.writeValueAsBytes(object);
    }

    @SneakyThrows
    public static String toJsonPrettyString(Object object) {
        return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        if (!StringUtils.hasText(text)) {
            return null;
        }
        try {
            return MAPPER.readValue(text, clazz);
        } catch (IOException e) {
            log.error("json parse err,json:{}", text, e);
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseObject(byte[] bytes, Class<T> clazz) {
        if (ArrayUtil.isEmpty(bytes)) {
            return null;
        }
        try {
            return MAPPER.readValue(bytes, clazz);
        } catch (IOException e) {
            log.error("json parse err,json:{}", bytes, e);
            throw new RuntimeException(e);
        }
    }

    public static <T> T parseObject(String text, TypeReference<T> typeReference) {
        try {
            return MAPPER.readValue(text, typeReference);
        } catch (IOException e) {
            log.error("json parse err,json:{}", text, e);
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        if (!StringUtils.hasText(text)) {
            return new ArrayList<>();
        }
        try {
            return MAPPER.readValue(text, MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            log.error("json parse err,json:{}", text, e);
            throw new RuntimeException(e);
        }
    }

    public static JsonNode parseTree(String text) {
        try {
            return MAPPER.readTree(text);
        } catch (IOException e) {
            log.error("json parse err,json:{}", text, e);
            throw new RuntimeException(e);
        }
    }

    public static JsonNode parseTree(byte[] text) {
        try {
            return MAPPER.readTree(text);
        } catch (IOException e) {
            log.error("json parse err,json:{}", text, e);
            throw new RuntimeException(e);
        }
    }
}
