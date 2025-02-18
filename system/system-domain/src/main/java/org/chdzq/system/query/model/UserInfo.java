package org.chdzq.system.query.model;

import lombok.Value;

import java.util.List;

/**
 * 用户信息
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/4 19:36
 */
@Value
public class UserInfo {
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
     * 角色列表
     */
    List<RoleVO> roles;

    /**
     * 权限列表
     */
    List<ResourceTreeVO> resources;


}
