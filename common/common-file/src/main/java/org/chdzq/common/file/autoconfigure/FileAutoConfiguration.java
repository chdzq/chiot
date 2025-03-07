package org.chdzq.common.file.autoconfigure;

import org.chdzq.common.file.core.service.OssService;
import org.chdzq.common.file.core.service.aliyun.AliyunOssConfig;
import org.chdzq.common.file.core.service.aliyun.AliyunOssServiceImpl;
import org.chdzq.common.file.core.service.minio.MinioOssServiceImpl;
import org.chdzq.common.file.core.service.minio.MinoConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 文件自动配置类
 *
 * @author chdzq
 * @create 2025/3/7 21:58
 */
@EnableConfigurationProperties(value = {AliyunOssConfig.class, MinoConfig.class})
@Configuration
public class FileAutoConfiguration {

    @ConditionalOnProperty(value = "file.oss.type", havingValue = "minio", matchIfMissing = true)
    @Bean
    OssService minioOssService(MinoConfig minoConfig) {
        return new MinioOssServiceImpl(minoConfig);
    }

    @ConditionalOnProperty(value = "file.oss.type", havingValue = "aliyun")
    @Bean
    OssService aliyunOssService(AliyunOssConfig aliyunOssConfig) {
        return new AliyunOssServiceImpl(aliyunOssConfig);
    }
}
