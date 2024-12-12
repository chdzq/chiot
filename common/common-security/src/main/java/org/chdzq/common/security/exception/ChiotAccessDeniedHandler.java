package org.chdzq.common.security.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.chdzq.common.core.entity.Result;
import org.chdzq.common.core.result.ResultError;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

/**
 * 自定义无权限异常
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/27 14:19
 */
//@Component
public final class ChiotAccessDeniedHandler implements AccessDeniedHandler {

//    private final MappingJackson2HttpMessageConverter objectMapper;

    private final HttpMessageConverter<Object> httpConverter;

    public ChiotAccessDeniedHandler(HttpMessageConverter<Object> httpConverter) {
        this.httpConverter = httpConverter;
    }

//    public ChiotAccessDeniedHandler(MappingJackson2HttpMessageConverter objectMapper) {
//        this.objectMapper = objectMapper;
//    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);
        httpConverter.write(Result.fail(ResultError.ACCESS_TOKEN_ERROR), null,  httpResponse);
    }
}
