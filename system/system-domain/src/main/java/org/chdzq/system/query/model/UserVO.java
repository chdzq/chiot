package org.chdzq.system.query.model;

import lombok.Builder;
import lombok.Value;

/**
 * 用户展示类
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/17 00:11
 */
@Value
@Builder
public class UserVO {
    /**
     * 用户id
     */
    Long id;

    /**
     * 账户名
     */
    String username;

    /**
     * 昵称
     */
    String nickname;

    /**
     * email
     */
    String email;

    /**
     * 手机号
     */
    String mobile;

    /**
     * 状态: 1->启用;0->禁用
     */
    Integer status;

    /**
     * 性别(1->男；2->女)
     */
    Integer gender;

    /**
     * 部门ID
     */
    Long departmentId;

    /**
     * 部门名称
     */
    String departmentName;
}
