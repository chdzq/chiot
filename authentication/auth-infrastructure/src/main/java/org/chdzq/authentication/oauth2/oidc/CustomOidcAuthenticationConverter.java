package org.chdzq.authentication.oauth2.oidc;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationConverter;

/**
 * 自定义 OIDC 认证转换器
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/29 15:42
 */
public class CustomOidcAuthenticationConverter implements AuthenticationConverter {

    private final CustomOidcUserInfoService customOidcUserInfoService;

    public CustomOidcAuthenticationConverter(CustomOidcUserInfoService customOidcUserInfoService) {
        this.customOidcUserInfoService = customOidcUserInfoService;
    }

    @Override
    public Authentication convert(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OidcUserInfo customOidcUserInfo = customOidcUserInfoService.loadUserByUsername(authentication.getName());
        return new OidcUserInfoAuthenticationToken(authentication, customOidcUserInfo);
    }
}
