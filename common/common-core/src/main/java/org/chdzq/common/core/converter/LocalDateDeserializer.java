package org.chdzq.common.core.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.chdzq.common.core.utils.DateTimeUtil;
import org.springframework.core.convert.converter.Converter;

import java.io.IOException;
import java.time.LocalDate;

/**
 * LocalDate反序列化规则
 * <p>
 * 会将字符串反序列化为LocalDate
 * @author chdzq
 * @create 2025/2/19 22:53
 */
public class LocalDateDeserializer extends JsonDeserializer<LocalDate> implements Converter<String, LocalDate> {

    public static final LocalDateDeserializer INSTANCE = new LocalDateDeserializer();

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return DateTimeUtil.parseDate(p.getText());
    }

    @Override
    public LocalDate convert(String source) {
        return DateTimeUtil.parseDate(source);
    }
}
