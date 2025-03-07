package org.chdzq.common.file.core.service.aliyun;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 阿里云OSS配置
 *
 * @author chdzq
 * @create 2025/3/7 21:50
 */
@ConditionalOnProperty(value = "file.oss.type", havingValue = "aliyun")
@ConfigurationProperties(prefix = "file.oss.aliyun")
@Data
public class AliyunOssConfig {
    /**
     * 服务Endpoint
     */
    private String endpoint;
    /**
     * 访问凭据
     */
    private String accessKeyId;
    /**
     * 凭据密钥
     */
    private String accessKeySecret;
    /**
     * 存储桶名称
     */
    private String bucketName;
}
