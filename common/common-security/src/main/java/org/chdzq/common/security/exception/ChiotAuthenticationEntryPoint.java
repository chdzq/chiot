package org.chdzq.common.security.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.chdzq.common.core.result.Result;
import org.chdzq.common.core.result.ResultError;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * 自定义 token 无效异常处理
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/27 14:31
 */
//@Component
public final class ChiotAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final HttpMessageConverter<Object> httpConverter;

    public ChiotAuthenticationEntryPoint(HttpMessageConverter<Object> httpConverter) {
        this.httpConverter = httpConverter;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);
        httpConverter.write(Result.fail(ResultError.ACCESS_TOKEN_ERROR), null, httpResponse);
//        objectMapper.writeValue(response.getOutputStream(), Result.fail(ResultError.ACCESS_UNAUTHORIZED_ERROR));
    }

//    private ObjectMapper objectMapper;
//
//    @Autowired
//    public void setObjectMapper(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }
}
