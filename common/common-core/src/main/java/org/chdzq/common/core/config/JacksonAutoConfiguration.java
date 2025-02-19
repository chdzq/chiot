package org.chdzq.common.core.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;
import org.chdzq.common.core.utils.JsonUtil;
import org.chdzq.common.core.converter.LocalDateDeserializer;
import org.chdzq.common.core.converter.LocalDateSerializer;
import org.chdzq.common.core.converter.LocalDateTimeDeserializer;
import org.chdzq.common.core.converter.LocalDateTimeSerializer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Jackson 自动配置类
 * @author chdzq
 * @create 2022/11/11
 */
@Slf4j
@Configuration
public class JacksonAutoConfiguration {

    @Bean
    public BeanPostProcessor objectMapperBeanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (!(bean instanceof ObjectMapper objectMapper)) {
                    return bean;
                }
                SimpleModule simpleModule = new SimpleModule();
                /*
                 * 2. 新增LocalDateTime序列化、反序列化规则
                 */
                simpleModule
                        .addSerializer(LocalDateTime.class, LocalDateTimeSerializer.INSTANCE)
                        .addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE)
                        .addSerializer(LocalDate.class, LocalDateSerializer.INSTANCE)
                        .addDeserializer(LocalDate.class, LocalDateDeserializer.INSTANCE);
                objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                // 反序列化时，忽略目标对象没有的属性
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                objectMapper.registerModules(simpleModule);

                JsonUtil.init(objectMapper);
                return bean;
            }
        };
    }

    @Bean
    public Converter<String, LocalDate> localDateConverter() {
        return new LocalDateDeserializer();
    }

    @Bean
    public Converter<String, LocalDateTime> localDateTimeConverter() {
        return new LocalDateTimeDeserializer();
    }

    @Bean
    public Converter<LocalDate, String> localDateSerializerConverter() {
        return new LocalDateSerializer();
    }

    @Bean
    public Converter<LocalDateTime, String> localDateTimeSerializerConverter() {
        return new LocalDateTimeSerializer();
    }
}
