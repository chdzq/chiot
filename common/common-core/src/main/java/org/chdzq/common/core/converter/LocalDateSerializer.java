package org.chdzq.common.core.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.chdzq.common.core.utils.DateTimeUtil;
import org.springframework.core.convert.converter.Converter;

import java.io.IOException;
import java.time.LocalDate;

/**
 * LocalDate序列化规则
 * <p>
 * 会将LocalDate序列化为字符串
 * @author chdzq
 * @create 2025/2/19 22:53
 */
public class LocalDateSerializer extends JsonSerializer<LocalDate> implements Converter<LocalDate, String> {

    public static final LocalDateSerializer INSTANCE = new LocalDateSerializer();

    @Override
    public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(DateTimeUtil.format(value));
    }

    @Override
    public String convert(LocalDate source) {
        return DateTimeUtil.format(source);
    }
}
