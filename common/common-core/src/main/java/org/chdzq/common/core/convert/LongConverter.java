package org.chdzq.common.core.convert;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 转换类
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/27 15:19
 */
@Component
public class LongConverter implements Converter<Object, Long> {

    @Override
    public Long convert(Object source) {
        if (source == null) {
            return null;
        }

        if (source instanceof Long) {
            return (Long) source;
        }
        if (source instanceof String) {
            return Long.valueOf((String) source);
        }

        return Long.valueOf(source.toString());
    }
}
