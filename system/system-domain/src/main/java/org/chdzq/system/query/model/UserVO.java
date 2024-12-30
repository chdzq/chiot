package org.chdzq.system.query.model;

import lombok.Builder;
import lombok.Getter;

/**
 * 用户展示类
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/17 00:11
 */
@Getter
@Builder
public class UserVO {
    /**
     * 用户id
     */
    private final Long id;

    /**
     * 账户名
     */
    private final String username;

    /**
     * 昵称
     */
    private final String nickname;

    /**
     * email
     */
    private final String email;

    /**
     * 手机号
     */
    private final String mobile;

    /**
     * 状态: 1->启用;0->禁用
     */
    private final Integer status;

    /**
     * 性别(1->男；2->女)
     */
    private final Integer gender;
}
