package org.chdzq.common.mybatis.autoconfigure;

import org.chdzq.common.mybatis.core.service.DbTypeDefaultFinalizer;
import org.chdzq.common.mybatis.core.service.DbTypeFinalizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBaits 配置类
 *
 * @author chdzq
 * @create 2022/11/11
 */
@Configuration
public class MybatisAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(DbTypeFinalizer.class)
    public DbTypeFinalizer dbTypeFinalizer() {
        return new DbTypeDefaultFinalizer();
    }
}
