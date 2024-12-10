package org.chdzq.authentication.oauth2.oidc;

/**
 * 扩充的OIDC 用户信息字段
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/29 15:36
 */
public interface CustomClaimNames {

    /**
     * 用户名
     */
    String USER_NAME = "username";

    /**
     * 状态 1是启用 0是禁用
     */
    String STATUS = "status";
}
