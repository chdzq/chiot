package org.chdzq.common.web.core.advice;

import org.chdzq.common.core.entity.Result;
import org.chdzq.common.web.core.annotation.IgnoreJsonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * @Description 统一响应处理
 * @Author chdzq
 * @Date 2024/11/14
 */
@RestControllerAdvice
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (Objects.isNull(returnType.getMethod())) {
            return false;
        }
        // 只拦截返回结果为 Result 类型
        IgnoreJsonResponse skipAnnotation = returnType.getMethod().getAnnotation(IgnoreJsonResponse.class);
        if (Objects.nonNull(skipAnnotation)) {
            return false;
        }
        Class<?> type = returnType.getParameterType();
        return  !Result.class.isAssignableFrom(type)
                && !ModelAndView.class.isAssignableFrom(type);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (!Objects.equals(selectedContentType, MediaType.APPLICATION_JSON)) {
            return body;
        }
        // 记录 Controller 结果
        Class<?> type = returnType.getParameterType();
        if (Result.class.isAssignableFrom(type)) {
            return body;
        } else {
            return Result.ok(body);
        }
    }
}
