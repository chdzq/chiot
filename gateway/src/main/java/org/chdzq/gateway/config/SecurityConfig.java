package org.chdzq.gateway.config;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 客户端配置
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/29 21:58
 */
@Setter
@ConfigurationProperties(prefix = "security")
@Configuration(proxyBeanMethods = false)
@EnableWebFluxSecurity
@Slf4j
public class SecurityConfig {

    /**
     * 黑名单请求路径列表
     */
    private List<String> blackPathList;

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {

        http.authorizeExchange((exchange) ->
                {
                    if (!CollectionUtils.isEmpty(blackPathList)) {
                        exchange.pathMatchers(blackPathList.toArray(new String[0])).denyAll();
                    }
                    exchange.anyExchange().permitAll();
                })
                .csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }

}
