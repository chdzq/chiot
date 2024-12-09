package org.chdzq.common.web.autoconfigure;

import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.optionals.OptionalDecoder;
import org.chdzq.common.web.core.fegin.FeginRequestInterceptor;
import org.chdzq.common.web.core.fegin.FeignDecoder;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * fegin自动配置类
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 17:54
 */
@Configuration
public class FeignAutoConfiguration {

    @Bean
    public Decoder feignDecoder(ObjectProvider<HttpMessageConverters> messageConverters) {
        return new OptionalDecoder((new ResponseEntityDecoder(new FeignDecoder(new SpringDecoder(messageConverters)))));
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeginRequestInterceptor();
    }
}
