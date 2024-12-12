package org.chdzq.system.entity;

import lombok.Data;
import org.chdzq.common.core.enums.DataScopeEnum;
import org.chdzq.common.core.enums.GenderEnum;
import org.chdzq.common.core.enums.StatusEnum;

import java.util.Set;

/**
 * 认证信息
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/13 00:17
 */
@Data
public class AuthInfo {

    /**
     * 扩展字段：用户ID
     */
    private Long id;

    /**
     * 扩展字段：部门ID
     */
    private Long deptId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户名
     */
    private String nickname;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户状态(1:正常;0:禁用)
     */
    private StatusEnum status;

    /**
     * 用户性别(1:男;2:女)
     */
    private GenderEnum gender;


    /**
     * 角色
     */
    private Set<String> roles;

    /**
     * 用户权限标识集合
     */
    private Set<String> perms;

    /**
     * 用户角色数据权限集合
     */
    private DataScopeEnum dataScope;

}
