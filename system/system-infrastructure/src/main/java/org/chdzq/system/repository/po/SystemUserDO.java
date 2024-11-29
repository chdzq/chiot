package org.chdzq.system.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.chdzq.common.mybatis.domain.BaseDO;

/**
 * 系统用户
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/27 23:58
 */
@TableName("sys_user")
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemUserDO extends BaseDO {
    /**
     * 用户id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 账户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * email
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态: 1->启用;0->禁用
     */
    private Integer status;

    /**
     * 性别(1->男；2->女)
     */
    private Integer gender;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 部门
     */
    private Long deptId;
}
