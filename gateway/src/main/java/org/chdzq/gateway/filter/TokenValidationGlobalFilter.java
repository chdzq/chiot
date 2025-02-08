package org.chdzq.gateway.filter;

import com.nimbusds.jose.JWSObject;
import com.nimbusds.oauth2.sdk.token.BearerTokenError;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.chdzq.common.core.constants.RedisConstant;
import org.chdzq.gateway.utils.WebFluxUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;

/**
 * TODO
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/29 20:53
 */
@Slf4j
@Component
@AllArgsConstructor
public class TokenValidationGlobalFilter implements GlobalFilter {

    private final RedisTemplate<String, Object> redisTemplate;

    private static final String BEARER_PREFIX = OAuth2AccessToken.TokenType.BEARER.getValue();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.hasText(authorization) || !StringUtils.startsWithIgnoreCase(authorization, BEARER_PREFIX)) {
            return chain.filter(exchange);
        }

        try {
            String token = authorization.substring(BEARER_PREFIX.length());
            JWSObject jwsObject = JWSObject.parse(token);
            String jti = (String) jwsObject.getPayload().toJSONObject().get("jti");
            String reason = (String)redisTemplate.opsForValue().get(RedisConstant.TOKEN_BLACKLIST_PREFIX + jti);
            if (StringUtils.hasText(reason)) {
                return WebFluxUtil.writeErrorResponse(response, BearerTokenError.MISSING_TOKEN);
            }
        } catch (ParseException e) {
            log.error("Parsing token failed in TokenValidationGlobalFilter", e);
            return WebFluxUtil.writeErrorResponse(response, BearerTokenError.INVALID_TOKEN);
        }

        return chain.filter(exchange);    }
}
