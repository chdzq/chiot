package org.chdzq.common.core.config;

import org.chdzq.common.core.dictionary.DictionaryConstant;
import org.chdzq.common.core.dictionary.DictionaryConstantRegister;
import org.chdzq.common.core.dictionary.DictionaryConstantService;
import org.chdzq.common.core.dictionary.impl.DictionaryConstantServiceImpl;
import org.chdzq.common.core.enums.GenderEnum;
import org.chdzq.common.core.enums.ResourceEnum;
import org.chdzq.common.core.enums.StatusEnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 常量字典表注册
 *
 * @author chdzq
 * @create 2025/2/14 11:43
 */
@Configuration
public class ConstantDictionaryAutoConfiguration {

    @Bean
    DictionaryConstantRegister register() {
        return new DictionaryConstantRegister()
                .registerConstantEnum(GenderEnum.class, "gender")
                .registerConstantEnum(ResourceEnum.class, "resource")
                .registerConstantEnum(StatusEnum.class, "status");
    }

    @Bean
    DictionaryConstantService dictionaryConstantService() {
        return new DictionaryConstantServiceImpl();
    }
}
