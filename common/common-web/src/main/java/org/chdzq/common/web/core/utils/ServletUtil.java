package org.chdzq.common.web.core.utils;

import org.chdzq.common.core.entity.Result;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.util.Objects;

/**
 * TODO
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/29 14:01
 */
public class ServletUtil {

    private static final HttpMessageConverter<Object> httpConverter = new MappingJackson2HttpMessageConverter();
    /**
     * Servlet 响应工具类
     * @param obj
     * @param outputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    public void write(Object obj, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        Result r;
        if (Objects.isNull(obj)) {
            r = Result.ok();
        } else if (Objects.equals(obj.getClass(), Result.class)) {
            r = (Result) obj;
        } else {
            r = Result.ok(obj);
        }

        httpConverter.write(r, null, outputMessage);
    }
}
