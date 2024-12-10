package org.chdzq.authentication.oauth2.extension.password;

import jakarta.servlet.http.HttpServletRequest;
import org.chdzq.authentication.oauth2.utils.OAuth2EndpointUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 密码模式参数解析器
 * 解析请求参数中的用户名和密码，并构建相应的身份验证(Authentication)对象
 * @author chdzq
 * @version 1.0
 * @date 2024/11/27 16:04
 */
public class PasswordAuthenticationConverter implements AuthenticationConverter {
    @Override
    public Authentication convert(HttpServletRequest request) {
        //授权类型
        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
        if (!PasswordAuthenticationToken.PASSWORD.getValue().equals(grantType)) {
            return null;
        }

        //客户端信息
        Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();

        //提取参数
        MultiValueMap<String, String> parameters = OAuth2EndpointUtil.getParameters(request);

        // 用户名验证(必需)
        String username = parameters.getFirst(OAuth2ParameterNames.USERNAME);
        if (!StringUtils.hasText(username)) {
            OAuth2EndpointUtil.throwInvalidParameterError(OAuth2ParameterNames.USERNAME);
        }

        // 密码验证(必需)
        String password = parameters.getFirst(OAuth2ParameterNames.PASSWORD);
        if (!StringUtils.hasText(password)) {
            OAuth2EndpointUtil.throwInvalidParameterError(OAuth2ParameterNames.PASSWORD);
        }

        // 令牌申请访问范围验证 (可选)
        String scope = parameters.getFirst(OAuth2ParameterNames.SCOPE);
        if (StringUtils.hasText(scope) &&
                parameters.get(OAuth2ParameterNames.SCOPE).size() != 1) {
            OAuth2EndpointUtil.throwInvalidParameterError(OAuth2ParameterNames.SCOPE);
        }

        Set<String> requestedScopes;
        if (StringUtils.hasText(scope)) {
            requestedScopes = new HashSet<>(Arrays.asList(StringUtils.delimitedListToStringArray(scope, ",")));
        } else {
            requestedScopes = new HashSet<>();
        }

        requestedScopes.add(OidcScopes.OPENID);

        Set<String> filterParameter = Set.of(OAuth2ParameterNames.GRANT_TYPE, OAuth2ParameterNames.SCOPE);
        // 附加参数(保存用户名/密码传递给 PasswordAuthenticationProvider 用于身份认证)
        Map<String, Object> additionalParameters = parameters.entrySet()
                .stream()
                .filter(e -> !filterParameter.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().get(0)));

        return new PasswordAuthenticationToken(clientPrincipal, requestedScopes, additionalParameters);
    }
}
