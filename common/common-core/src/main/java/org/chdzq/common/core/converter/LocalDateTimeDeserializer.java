package org.chdzq.common.core.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.chdzq.common.core.utils.DateTimeUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * LocalDateTime反序列化规则
 * <p>
 * 会将字符串时间戳反序列化为LocalDateTime
 * @author chdzq
 * @create 2025/2/19 22:53
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> implements Converter<String, LocalDateTime> {

    public static final LocalDateTimeDeserializer INSTANCE = new LocalDateTimeDeserializer();

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return convert(p.getText());
    }

    @Override
    public LocalDateTime convert(String source) {
        if (StringUtils.hasText(source)) {
            return null;
        }
        if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            return DateTimeUtil.parse(source, DateTimeUtil.NORM_DATE_FORMATTER);
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            return DateTimeUtil.parse(source, DateTimeUtil.NORM_DATETIME_FORMATTER);
        }
        return DateTimeUtil.parse(source, null);
    }
}
