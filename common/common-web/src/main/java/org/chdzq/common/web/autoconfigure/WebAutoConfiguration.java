package org.chdzq.common.web.autoconfigure;

import jakarta.servlet.Filter;
import org.chdzq.common.web.core.constrants.WebFilterOrderConstant;
import org.chdzq.common.web.core.filter.UserRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 自动配置类
 * @author chdzq
 * @version 1.0
 * @date 2024/11/21 23:51
 */
@Configuration
public class WebAutoConfiguration {

    @Bean
    public FilterRegistrationBean<UserRequestFilter> filterRegistrationBean() {
        FilterRegistrationBean<UserRequestFilter> bean = createFilterBean(new UserRequestFilter(), WebFilterOrderConstant.USER_FILTER);
        bean.addUrlPatterns("/*");
        return bean;
    }

    private static <T extends Filter> FilterRegistrationBean<T> createFilterBean(T filter, Integer order) {
        FilterRegistrationBean<T> bean = new FilterRegistrationBean<>(filter);
        bean.setOrder(order);
        return bean;
    }
}
