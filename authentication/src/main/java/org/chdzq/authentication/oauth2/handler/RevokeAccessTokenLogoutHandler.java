package org.chdzq.authentication.oauth2.handler;

import com.nimbusds.jose.JWSObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.chdzq.common.core.constants.JwtClaimConstant;
import org.chdzq.common.core.constants.RedisConstant;
import org.chdzq.common.core.entity.Result;
import org.chdzq.common.core.utils.JsonUtil;
import org.chdzq.common.security.utils.UserContext;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.web.OAuth2TokenRevocationEndpointFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 登出处理-撤销token
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/21 17:18
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class RevokeAccessTokenLogoutHandler implements LogoutHandler {
    private final OAuth2AuthorizationService authorizationService;
    private final RedisTemplate<String, Object> redisTemplate;

    private static final String BEARER_PREFIX = OAuth2AccessToken.TokenType.BEARER.getValue();


    @Override
    @SneakyThrows
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 获取token(可以是Access Token，也可以是Refresh Token)
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token;
        if (ObjectUtils.isEmpty(authorization)) {
            token = null;
        } else {
            token = authorization.substring(BEARER_PREFIX.length());
        }

        if(!StringUtils.hasText(token)){
            return;
        }
        doRevokeTokenFromRedis(token);

        OAuth2Authorization oAuth2Authorization = authorizationService.findByToken(token, OAuth2TokenType.ACCESS_TOKEN);
        if(Objects.isNull(oAuth2Authorization)){
            return;
        }

        OAuth2Authorization.Token<OAuth2AccessToken> accessToken = oAuth2Authorization.getAccessToken();
        if(Objects.isNull(accessToken) || !StringUtils.hasText(accessToken.getToken().getTokenValue())){
            return;
        }
        authorizationService.remove(oAuth2Authorization);
    }

    private void doRevokeTokenFromRedis(String token) throws ParseException {
        JWSObject jwsObject = JWSObject.parse(token);
        Map<String, Object> jsonObject = jwsObject.getPayload().toJSONObject();
        String jti = (String) jsonObject.get(JwtClaimConstant.JTI);
        if (!StringUtils.hasText(jti)) {
            return;
        }
        Object expireTimeOpt = jsonObject.get(JwtClaimConstant.EXP);
        if (Objects.isNull(expireTimeOpt)) {
            // token 永不过期则永久加入黑名单
            redisTemplate.opsForValue().set(RedisConstant.TOKEN_BLACKLIST_PREFIX + jti, "logout");
        } else {
            long expireTime = Long.parseLong(expireTimeOpt.toString());
            long currentTimeInSeconds = System.currentTimeMillis() / 1000; // 当前时间（单位：秒）
            if (expireTime > currentTimeInSeconds) {
                // token未过期，添加至缓存作为黑名单，缓存时间为token剩余的有效时间
                long remainingTimeInSeconds = expireTime - currentTimeInSeconds;
                redisTemplate.opsForValue().set(RedisConstant.TOKEN_BLACKLIST_PREFIX + jti, "logout", remainingTimeInSeconds, TimeUnit.SECONDS);
            }
        }
    }
}
