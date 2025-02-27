package org.chdzq.authentication.oauth2.oidc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationContext;
import org.springframework.security.oauth2.server.authorization.oidc.authentication.OidcUserInfoAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;
import org.springframework.util.Assert;

import java.util.*;
import java.util.function.Function;

/**
 * 自定义 OIDC 认证提供者
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/29 15:44
 */
@Slf4j
public class CustomOidcAuthenticationProvider implements AuthenticationProvider {

    private final OAuth2AuthorizationService authorizationService;

    public CustomOidcAuthenticationProvider(OAuth2AuthorizationService authorizationService) {
        Assert.notNull(authorizationService, "authorizationService cannot be null");
        this.authorizationService = authorizationService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        OidcUserInfoAuthenticationToken userInfoAuthentication = (OidcUserInfoAuthenticationToken) authentication;
        AbstractOAuth2TokenAuthenticationToken<?> accessTokenAuthentication = null;
        if (AbstractOAuth2TokenAuthenticationToken.class.isAssignableFrom(userInfoAuthentication.getPrincipal().getClass())) {
            accessTokenAuthentication = (AbstractOAuth2TokenAuthenticationToken<?>) userInfoAuthentication.getPrincipal();
        }
        if (Objects.isNull(accessTokenAuthentication) || !accessTokenAuthentication.isAuthenticated()) {
            throw new OAuth2AuthenticationException("invalid_token");
        }

        String accessTokenValue = accessTokenAuthentication.getToken().getTokenValue();
        OAuth2Authorization authorization = this.authorizationService.findByToken(accessTokenValue, OAuth2TokenType.ACCESS_TOKEN);
        if (Objects.isNull(authorization)) {
            throw new OAuth2AuthenticationException("invalid_token");
        }
        if (log.isTraceEnabled()) {
            log.trace("Retrieved authorization with access token");
        }

        OAuth2Authorization.Token<OAuth2AccessToken> authorizedAccessToken = authorization.getAccessToken();
        if (!authorizedAccessToken.isActive()) {
            throw new OAuth2AuthenticationException("invalid_token");
        }
        // 从认证结果中获取userInfo
        OidcUserInfo customOidcUserInfo = userInfoAuthentication.getUserInfo();
        // 从authorizedAccessToken中获取授权范围
        Set<String> scopeSet = (Set<String>) authorizedAccessToken.getClaims().get("scope");
        // 获取授权范围对应userInfo的字段信息
        Map<String, Object> claims = DefaultOidcUserInfoMapper.getClaimsRequestedByScope(customOidcUserInfo.getClaims(), scopeSet);
        if (log.isTraceEnabled()) {
            log.trace("Authenticated user info request");
        }

        return new OidcUserInfoAuthenticationToken(accessTokenAuthentication, new OidcUserInfo(claims));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OidcUserInfoAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private static final class DefaultOidcUserInfoMapper implements Function<OidcUserInfoAuthenticationContext, OidcUserInfo> {

        private static final Set<String> ADDRESS_CLAIMS = Set.of(
                StandardClaimNames.ADDRESS
        );
        private static final Set<String> EMAIL_CLAIMS = Set.of(
                StandardClaimNames.EMAIL,
                StandardClaimNames.EMAIL_VERIFIED
        );
        private static final Set<String> PHONE_CLAIMS = Set.of(
                StandardClaimNames.PHONE_NUMBER,
                StandardClaimNames.PHONE_NUMBER_VERIFIED
        );
        private static final Set<String> PROFILE_CLAIMS = Set.of(
                CustomClaimNames.USER_NAME,
                StandardClaimNames.NICKNAME,
                CustomClaimNames.STATUS,
                StandardClaimNames.PROFILE,
                StandardClaimNames.PICTURE,
                StandardClaimNames.PREFERRED_USERNAME
        );

        private DefaultOidcUserInfoMapper() {}

        @Override
        public OidcUserInfo apply(OidcUserInfoAuthenticationContext authenticationContext) {
            OAuth2Authorization authorization = authenticationContext.getAuthorization();
            OidcIdToken idToken = Objects.requireNonNull(authorization.getToken(OidcIdToken.class)).getToken();
            OAuth2AccessToken accessToken = authenticationContext.getAccessToken();
            Map<String, Object> scopeRequestedClaims = getClaimsRequestedByScope(idToken.getClaims(), accessToken.getScopes());
            return new OidcUserInfo(scopeRequestedClaims);
        }

        /**
         * 根据不同权限抽取不同数据
         */
        private static Map<String, Object> getClaimsRequestedByScope(Map<String, Object> claims, Set<String> requestedScopes) {
            Set<String> scopeClaimNames = new HashSet<>(32);
            scopeClaimNames.add(StandardClaimNames.SUB);

            if (requestedScopes.contains(OidcScopes.ADDRESS)) {
                scopeClaimNames.addAll(ADDRESS_CLAIMS);
            }

            if (requestedScopes.contains(OidcScopes.EMAIL)) {
                scopeClaimNames.addAll(EMAIL_CLAIMS);
            }

            if (requestedScopes.contains(OidcScopes.PHONE)) {
                scopeClaimNames.addAll(PHONE_CLAIMS);
            }

            if (requestedScopes.contains(OidcScopes.PROFILE)) {
                scopeClaimNames.addAll(PROFILE_CLAIMS);
            }

            Map<String, Object> requestedClaims = new HashMap<>(claims);
            requestedClaims.keySet().removeIf((claimName) -> !scopeClaimNames.contains(claimName));
            return requestedClaims;
        }
    }
}
