package org.chdzq.common.core.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.chdzq.common.core.utils.DateTimeUtil;
import org.springframework.core.convert.converter.Converter;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * LocalDateTime序列化规则
 * <p>
 * 会将LocalDateTime序列化为字符串
 * @author chdzq
 * @create 2025/2/19 22:53
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> implements Converter<LocalDateTime, String> {

    public static final LocalDateTimeSerializer INSTANCE = new LocalDateTimeSerializer();

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(DateTimeUtil.format(value));
    }

    @Override
    public String convert(LocalDateTime source) {
        return DateTimeUtil.format(source);
    }
}
