package org.chdzq.authentication.oauth2.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.chdzq.common.core.result.Result;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.bind.ServletRequestUtils;

import java.io.IOException;

/**
 * 认证失败处理器
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/29 13:52
 */
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final HttpMessageConverter<Object> httpConverter;

    public CustomAuthenticationFailureHandler(HttpMessageConverter<Object> httpConverter) {
        this.httpConverter = httpConverter;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        OAuth2Error error = ((OAuth2AuthenticationException) exception).getError();
        ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);
        Result<?> result = Result.fail(error.getErrorCode(), error.getDescription());
        httpConverter.write(result, null, httpResponse);
    }
}
