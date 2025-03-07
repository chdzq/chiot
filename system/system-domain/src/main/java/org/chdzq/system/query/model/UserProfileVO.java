package org.chdzq.system.query.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户简介
 *
 * @author chdzq
 * @create 2025/3/7 22:42
 */
@Data
public class UserProfileVO {
    /**
     * 用户ID
     */
    private Long id;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 用户角色名称集合
     */
    private List<String> roleNames;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

}