package org.chdzq.authentication.oauth2.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.chdzq.common.core.entity.Result;
import org.chdzq.common.core.utils.JsonUtil;
import org.chdzq.common.web.core.utils.HttpEndpointUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 登出成功自定义处理
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/21 17:24
 */
@Slf4j
public class JsonLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("onLogoutSuccessHandler");
        response.setStatus(HttpStatus.OK.value());

        // 写回json数据
        Result<String> result = Result.ok();
        ServletServerHttpResponse httpResponse = new ServletServerHttpResponse(response);
        HttpEndpointUtil.writeWithMessageConverters(result, httpResponse);

    }

}
