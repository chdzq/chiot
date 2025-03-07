package org.chdzq.common.file.core.service.minio;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * MinoConfig
 *
 * @author chdzq
 * @create 2025/3/7 20:34
 */
@ConditionalOnProperty(value = "file.oss.type", havingValue = "minio", matchIfMissing = true)
@ConfigurationProperties(prefix = "file.oss.minio")
@Data
public class MinoConfig {
    /**
     * 服务Endpoint(http://localhost:9000)
     */
    private String endpoint;
    /**
     * 访问凭据
     */
    private String accessKey;
    /**
     * 凭据密钥
     */
    private String secretKey;
    /**
     * 存储桶名称
     */
    private String bucketName;
    /**
     * 自定义域名(https://oss.chdzq.tech)
     */
    private String customDomain;

}
