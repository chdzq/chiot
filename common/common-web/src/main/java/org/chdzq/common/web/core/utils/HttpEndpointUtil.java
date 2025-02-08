package org.chdzq.common.web.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodProcessor;

import java.io.IOException;

/**
 * Http 工具类
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/2/7 17:23
 */
public class HttpEndpointUtil implements ApplicationContextAware {
    private static HttpMessageConverters messageConverters;

    /**
     * 写出响应数据
     * @param responseData /
     * @param outputMessage /
     * @throws IOException /
     * @see AbstractMessageConverterMethodProcessor AbstractMessageConverterMethodProcessor#writeWithMessageConverters
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T> void writeWithMessageConverters(T responseData, ServletServerHttpResponse outputMessage) throws IOException {
        for (HttpMessageConverter converter : HttpEndpointUtil.messageConverters) {
            Class<?> valueType = responseData.getClass();
            if(converter.canWrite(valueType, MediaType.APPLICATION_JSON)){
                converter.write(responseData, MediaType.APPLICATION_JSON, outputMessage);
                break;
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        HttpEndpointUtil.messageConverters = applicationContext.getBean(HttpMessageConverters.class);
    }
}
