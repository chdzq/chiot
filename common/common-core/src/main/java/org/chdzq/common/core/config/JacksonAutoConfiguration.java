package org.chdzq.common.core.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.chdzq.common.core.utils.JsonUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * Jackson
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/21 17:11
 */
@Slf4j
@Configuration
public class JacksonAutoConfiguration {
    @Bean
    public BeanPostProcessor objectMapperBeanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (!(bean instanceof ObjectMapper)) {
                    return bean;
                }
                ObjectMapper objectMapper = (ObjectMapper) bean;
                SimpleModule simpleModule = new SimpleModule();
                /*
                 * 2. 新增LocalDateTime序列化、反序列化规则
                 */
                simpleModule
                        .addSerializer(LocalDateTime.class, LocalDateTimeSerializer.INSTANCE)
                        .addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                // 反序列化时，忽略目标对象没有的属性
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                objectMapper.registerModules(simpleModule);

                JsonUtil.init(objectMapper);
                log.info("初始化 jackson 自动配置");
                return bean;
            }
        };
    }
}
