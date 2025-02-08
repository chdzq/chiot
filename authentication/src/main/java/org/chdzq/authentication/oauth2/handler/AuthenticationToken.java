package org.chdzq.authentication.oauth2.handler;

import lombok.Builder;
import lombok.Data;

/**
 * AuthenticationToken
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/11 16:31
 */
@Builder
@Data
public class AuthenticationToken {

    /**
     *访问令牌
     */
    private String accessToken;

    /**
     * 刷新令牌
     */
    private String refreshToken;

    /**
     * 过期时间 秒
     */
    private Long expiresIn;

    /**
     * 令牌类型
     */
    private String tokenType;
}
