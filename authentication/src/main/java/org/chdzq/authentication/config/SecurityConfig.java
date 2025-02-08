package org.chdzq.authentication.config;

import lombok.Setter;
import org.chdzq.authentication.oauth2.handler.JsonLogoutSuccessHandler;
import org.chdzq.authentication.oauth2.handler.RevokeAccessTokenLogoutHandler;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.List;

/**
 * 默认安全配置
 */
@Setter
@ConfigurationProperties(prefix = "security")
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * 白名单路径列表
     */
    private List<String> whitePathList;

    /**
     * Spring Security 安全过滤器链配置
     *
     * @param http 安全配置
     * @return 安全过滤器链
     */
    @Bean
    @Order(0)
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http,
                                                   HandlerMappingIntrospector introspector,
                                                   OAuth2AuthorizationService authorizationService,
                                                   RevokeAccessTokenLogoutHandler logoutHandler) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        http.authorizeHttpRequests((requests) ->
                        {
                            if (!CollectionUtils.isEmpty(whitePathList)) {
                                for (String whitelistPath : whitePathList) {
                                    requests.requestMatchers(mvcMatcherBuilder.pattern(whitelistPath)).permitAll();
                                }
                            }
                            requests.anyRequest().authenticated();
                        }
                )
                .csrf(AbstractHttpConfigurer::disable);


        // 自定义登出配置
        http.logout(logout -> logout
                // 登出时同步撤销Token
                .addLogoutHandler(logoutHandler)
                // 自定义登出成功响应处理
                .logoutSuccessHandler(new JsonLogoutSuccessHandler())
        );
        return http.build();
    }


    /**
     * 不走过滤器链的放行配置
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
                AntPathRequestMatcher.antMatcher("/webjars/**"),
                AntPathRequestMatcher.antMatcher("/doc.html"),
                AntPathRequestMatcher.antMatcher("/swagger-resources/**"),
                AntPathRequestMatcher.antMatcher("/v3/api-docs/**"),
                AntPathRequestMatcher.antMatcher("/swagger-ui/**")
        );
    }


}
