package org.chdzq.common.security.config;

import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.chdzq.common.core.constants.JwtClaimConstant;
import org.chdzq.common.security.exception.ChiotAccessDeniedHandler;
import org.chdzq.common.security.exception.ChiotAuthenticationEntryPoint;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.List;

/**
 * 资源服务器配置
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/27 13:32
 */
@ConfigurationProperties(prefix = "security")
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Setter
public class ResourceServerConfig {

    @Bean
    AccessDeniedHandler accessDeniedHandler(MappingJackson2HttpMessageConverter httpConverter) {
        return new ChiotAccessDeniedHandler(httpConverter);
    }

    @Bean
    AuthenticationEntryPoint authenticationEntryPoint(MappingJackson2HttpMessageConverter httpConverter) {
        return new ChiotAuthenticationEntryPoint(httpConverter);
    }

    private List<String> whitePathList;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   HandlerMappingIntrospector introspector,
                                                   AccessDeniedHandler accessDeniedHandler,
                                                   AuthenticationEntryPoint authenticationEntryPoint
    ) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);

        http.authorizeHttpRequests((requests)-> {
            if (!CollectionUtils.isEmpty(whitePathList)) {
                for (String url : whitePathList) {
                    requests.requestMatchers(mvcMatcherBuilder.pattern(url))
                            .permitAll();
                }
            }
            requests.anyRequest().authenticated();
        }).csrf(AbstractHttpConfigurer::disable);
//        http.oauth2ResourceServer((configurer)->{
//            configurer.jwt(jwtConfigurer -> jwtAuthenticationConverter())
//                    .authenticationEntryPoint(authenticationEntryPoint)
//                    .accessDeniedHandler(accessDeniedHandler);
//        });
        return http.build();
    }

    /**
     * 自定义JWT Converter
     *
     * @return Converter
     * @see JwtAuthenticationProvider#setJwtAuthenticationConverter(Converter)
     */
//    @Bean
//    public Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
//        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(Strings.EMPTY);
//        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(JwtClaimConstant.AUTHORITIES);
//
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
//        return jwtAuthenticationConverter;
//    }

}
